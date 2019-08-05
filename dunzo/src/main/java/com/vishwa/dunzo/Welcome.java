package com.vishwa.dunzo;

/**
*
* @author  Vishwanath G T, Shiva Chetan K S, Manasa D, Vinuta 
* @version 1.0
* @since   2019-08-04
* 
* All rights reserved. © Copyright 2019
* 
*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class Welcome {

	@GetMapping
	public String greeting() {
		return "Hello World!!!";
	}
}
