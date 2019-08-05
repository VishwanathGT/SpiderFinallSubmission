package com.vishwa.models;

/**
*
* @author  Vishwanath G T, Shiva Chetan K S, Manasa D, Vinuta 
* @version 1.0
* @since   2019-08-04
* 
* All rights reserved. © Copyright 2019
* 
*/

import java.util.List;

public class ShopDetails {

	private String shopName;
	private String address;
	private String phNo;
	
	private List<ItemDetails> itemList;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public List<ItemDetails> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemDetails> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "ShopDetails [shopName=" + shopName + ", address=" + address + ", phNo=" + phNo + "]";
	}
	
	
	
}
