package com.db.ows.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

	
	
	@GetMapping(value = "/getLoggedInUser")
	public String getLoggedInUser() {
		return "@GetMapping";
	}
	
	
}
