package com.db.ows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.ows.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService ls;

	@GetMapping(value = "/getLoggedInUser")
	public String getLoggedInUser() {
		return "@GetMapping";
	}

	@GetMapping(value = "/registerUser")
	public void registerUser() {
		ls.registerUser();
	}

}
