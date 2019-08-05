package com.vishwa.dunzo.rest;

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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishwa.databasemanager.DBConnector;
import com.vishwa.models.GetAllResponse;

@CrossOrigin	
@RestController
@RequestMapping("/allitems")
public class ItemsResource {

	@SuppressWarnings("unchecked")
	@GetMapping
	public ResponseEntity<List<GetAllResponse>> getAllItems() {
		List<GetAllResponse> allItems = DBConnector.getAllItems();
		
		return new ResponseEntity<List<GetAllResponse>>(allItems, HttpStatus.OK);
	}
	
	
}
