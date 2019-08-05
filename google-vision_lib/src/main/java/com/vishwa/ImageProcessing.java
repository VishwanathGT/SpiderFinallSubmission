package com.vishwa;

/**
*
* @author  Vishwanath G T, Shiva Chetan K S, Manasa D, Vinuta 
* @version 1.0
* @since   2019-08-04
* 
* All rights reserved. © Copyright 2019
* 
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.vision.Detect;
import com.vishwa.databasemanager.DBConnector;
import com.vishwa.models.ItemDetails;
import com.vishwa.models.ShopDetails;
import com.vishwa.models.TextItem;

public class ImageProcessing {

	//public static void main(String[] args) throws IOException, Exception {
		public static void processImage(String filename)  throws IOException, Exception {
		List<String> citis = new ArrayList<>();
		citis.add("Bangalore");
		citis.add("Chennai");
		citis.add("Pune");
		
		List<TextItem> detectText = Detect.detectText(filename, System.out);
		TextItem baseItem = detectText.get(0);
		detectText.remove(0);
		
		int diff = baseItem.getyMin() - baseItem.getyMax();
		int delta= 15;
		if(diff <= 485 ) {
			delta = 10;
		} else if(diff > 485 && diff <= 500 ) {
			delta = 11;
		} else if(diff >500 && diff <= 515 ) {
			delta = 12;
		} else if(diff >515 && diff <= 530 ) {
			delta = 13;
		} else if(diff >530 && diff <= 545 ) {
			delta = 14;
		}
		
		List<List<TextItem>> finalList = new ArrayList<>();
		for (int i = 0 ; i < detectText.size() ; i++) {
			TextItem itemCursor = detectText.get(i);
			List<TextItem> lineList = new ArrayList<>();
			lineList.add(itemCursor);
			for (int j = i+1 ; j < detectText.size() ; j++) {
				TextItem itemToScan = detectText.get(j);
				int d1 = itemCursor.getyMax() - itemToScan.getyMax();
				int d2 = itemCursor.getyMin() - itemToScan.getyMin();
				
				if((d1 >= -delta && d1 <= +delta) && (d2 >= -delta && d2 <= +delta)) {
					// if(itemToScan.getxMin() >  itemCursor.getxMax() ) {						
						lineList.add(itemToScan);
						itemCursor = itemToScan;
						detectText.remove(j);
						j--;
					// }
				}
			}
			lineList.sort((a, b) -> a.getxMin() - b.getxMin());
			finalList.add(lineList);
		}
		
		for (List<TextItem> list : finalList) {
			for (TextItem textItem : list) {
				System.out.print(textItem+" ");
			}
			System.out.println("");
		}
		ShopDetails shopDetails = new ShopDetails();
		List<TextItem> shopNameList = finalList.get(0);
		String shopName = "";
		for (TextItem textItem : shopNameList) {
			shopName += textItem.getText()+" ";
		}
		shopDetails.setShopName(shopName);
		finalList.remove(0);
		
		String address = "";
		boolean done = false;
		int i = 0 ;
		for(; i < finalList.size() ; i++) {
			List<TextItem> shopAddressList = finalList.get(i);
			for (TextItem textItem : shopAddressList) {
				address += textItem.getText()+" ";
			}
			String temp = new String(address);
			for (String citi : citis) {
				if(temp.toLowerCase().contains(citi.toLowerCase())) {
					shopDetails.setAddress(address);
					done = true;
					break;
				}
			}
			if(done) {
				break;
			}
		}
		
		done = false;
		int tempi = i;
		for( ; i < finalList.size() ; i++ ) {
			List<TextItem> shopAddressList = finalList.get(i);
			for (TextItem textItem : shopAddressList) {
				String text = textItem.getText();
				String[] split = text.split(":");
				
				if(Pattern.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", split[split.length-1])) {
					shopDetails.setPhNo(textItem.getText());
					done = true;
					break;
				}
			}
			if(done) {
				break;
			}
		}
		
		if(!done) {
			i = tempi;
		}
		
		done = false;
		int priceIndex = 0;
		List<String> billTableHeader = new ArrayList<>();
		for( ; i < finalList.size() ; i++ ) {
			List<TextItem> shopAddressList = finalList.get(i);
			TextItem textItem = shopAddressList.get(0);
			
			Pattern pattern = Pattern.compile("Ite[a-z]", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(textItem.getText());
			if(matcher.matches()) {
				// textItem = shopAddressList.get(1);
				priceIndex++;
				billTableHeader.add("Item Name");
				done = true;
				for (int j = 1 ; j < shopAddressList.size() ; j++) {
					textItem = shopAddressList.get(j);
					if(!textItem.getText().equalsIgnoreCase("name")) {						
						billTableHeader.add(textItem.getText());
					}
					if(textItem.getText().equalsIgnoreCase("price")) {						
						priceIndex++;
					}
				}
			}
			if(done) {
				break;
			}
			
		}
		
		System.out.println("priceIndex : "+priceIndex);
		done = false;
		List<ItemDetails> mainItemList = new ArrayList<>();
		for( ; i < finalList.size() ; i++ ) {
			List<TextItem> itemList = finalList.get(i);
			String itemName = "";
			int temp = 0;
			done = false;
			for(int j = 0 ; j < itemList.size() ; j++) {
			//for (TextItem textItem : itemList) {
				TextItem textItem = itemList.get(j);
				if(!Pattern.matches("[+-]?([0-9]*[.])+[0-9]+", textItem.getText())) {
					itemName += textItem.getText()+ " ";
				} else {
					done = true;
					temp = j;
					break;
				}
			}
			if(done && !itemName.isEmpty()) {
				if(itemName.contains("Sub") || itemName.contains("Total")) {
					break;
				}
				TextItem textItem = itemList.get(temp); // Adding PRICE
				ItemDetails itemDetails = new ItemDetails();
				itemDetails.setItemName(itemName);
				itemDetails.setPrice(textItem.getText());
				temp++;
				textItem = itemList.get(temp); // Adding QTY
				itemDetails.setQty(textItem.getText());
				mainItemList.add(itemDetails);
			}
			
		}
		
		
		
		System.out.println("----------------------------------------");
		System.out.println(shopDetails);
		System.out.println("---------------------------------------");
		System.out.println("Size : "+billTableHeader.size());
		for (String string : billTableHeader) {
			System.out.print(string+" ");
		}
		
		System.out.println("-----------------------------------------");
		for (ItemDetails itemDetails : mainItemList) {
			System.out.println("Name : "+itemDetails.getItemName()+"   Price : "+itemDetails.getPrice()+" Qty :"+itemDetails.getQty());
		}
		
		storeDetails(shopDetails, mainItemList);
		
	}
	
	public static void storeDetails(ShopDetails shopDetails, List<ItemDetails> itemList) {
		DBConnector.insertIntoShopDetails(shopDetails.getShopName().trim(), shopDetails.getAddress(), shopDetails.getPhNo());
		
		for (ItemDetails itemDetails : itemList) {
			DBConnector.insertIntoItemDetails(shopDetails.getShopName().trim(), itemDetails.getItemName(), itemDetails.getPrice());
		}
		
	}
	


}
