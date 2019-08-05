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

public class TextItem {

	private String text;
	private int xMin, xMax, yMin, yMax;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getxMin() {
		return xMin;
	}
	public void setxMin(int xMin) {
		this.xMin = xMin;
	}
	public int getxMax() {
		return xMax;
	}
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}
	public int getyMin() {
		return yMin;
	}
	public void setyMin(int yMin) {
		this.yMin = yMin;
	}
	public int getyMax() {
		return yMax;
	}
	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	@Override
	public String toString() {
		return text;
	}
	
	
}
