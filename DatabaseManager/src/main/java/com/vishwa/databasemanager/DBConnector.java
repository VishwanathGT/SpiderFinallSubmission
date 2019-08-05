package com.vishwa.databasemanager;

/**
*
* @author  Vishwanath G T, Shiva Chetan K S, Manasa D, Vinuta 
* @version 1.0
* @since   2019-08-04
* 
* All rights reserved. © Copyright 2019
* 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vishwa.models.GetAllResponse;
import com.vishwa.models.ItemDetails;
import com.vishwa.models.ShopDetails;

public class DBConnector {

	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dunzo", "root", "manage@2");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	public static void insertIntoShopDetails(String shopName, String address, String phNo) {
		try {
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			String selectQuery = "select * from SHOPDETAILS WHERE shopname = '"+shopName+"'";
			ResultSet rs = stmt.executeQuery(selectQuery);
			if (!rs.next()) {
			String query = "insert into SHOPDETAILS (shopname, address, phno) values ('" + shopName + "','" + address
					+ "','" + phNo + "')";
			stmt.execute(query);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// public static void main(String[] args) {
	public static void insertIntoItemDetails(String shopName, String itemName, String price) {
		try {
			Statement stmt = con.createStatement();
			String selectQuery = "select shopid from SHOPDETAILS WHERE shopname = '"+shopName+"'";
			ResultSet rs = stmt.executeQuery(selectQuery);
			String shopId = "";
			if (rs.next()) {
				shopId = rs.getString(1);
			}
			Date date = new Date();
			long time = date.getTime();
			String query = "insert into ITEMDETAILS (date, itemname, price, shopid) values ('" + time + "','" + itemName
					+ "','" + price + "'," + shopId + ")";
			// String query = "insert into ITEMDETAILS (date, itemname, price, shopid)
			// values ('"+time+"','test4','test4',"+shopId+")";
			stmt.execute(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static List<GetAllResponse> getAllItems() {
		List<ShopDetails> shopDetalsList = new ArrayList<ShopDetails>();
		try {
			Statement stmt = con.createStatement();
			String selectQuery = "select * from SHOPDETAILS";
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				int shopid = rs.getInt("shopid");
				String shopname = rs.getString("shopname");
				String address = rs.getString("address");
				String phno = rs.getString("phno");
				List<ItemDetails> itemsByShopID = getItemsByShopID(shopid);
				ShopDetails shopDetails = new ShopDetails();
				shopDetails.setShopName(shopname);
				shopDetails.setAddress(address);
				shopDetails.setPhNo(phno);
				shopDetails.setItemList(itemsByShopID);
				shopDetalsList.add(shopDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCustomeOutput(shopDetalsList);
	}
	
	public static List<ItemDetails> getItemsByShopID(int shopId) {
		List<ItemDetails> itemList = new ArrayList<ItemDetails>();
		try {
			Statement stmt = con.createStatement();
			String selectQuery = "select * from itemdetails where shopid = "+shopId+"";
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				long intdate = rs.getLong("date");
				String itemname = rs.getString("itemname");
				String price = rs.getString("price");
				
				Date date = new Date(intdate);
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				String format = df.format(date);
				
				ItemDetails item = new ItemDetails();
				item.setDate(format);
				item.setItemName(itemname);
				item.setPrice(price);
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return itemList;
	}
	
	public static List<GetAllResponse> getCustomeOutput(List<ShopDetails> shopDetalsList) {
		List<GetAllResponse> list = new ArrayList<GetAllResponse>();
		for (ShopDetails shop : shopDetalsList) {
			List<ItemDetails> itemList = shop.getItemList();
			for (ItemDetails itemDetails : itemList) {
				GetAllResponse res = new GetAllResponse();
				res.setShopName(shop.getShopName());
				res.setAddress(shop.getAddress());
				res.setPhNo(shop.getPhNo());
				res.setItemName(itemDetails.getItemName());
				res.setPrice(itemDetails.getPrice());
				list.add(res);
			}
		}
		return list;
	}
	
	

}
