package com.db.ows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.ows.model.User;
import com.db.ows.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService ls;

	@GetMapping(value = "/getLoggedInUser")
	public String getLoggedInUser() {
		System.out.println("gets called");
		return "@GetMapping";
	}
//
//	@RequestMapping( value = "/registerUser", method = RequestMethod.GET)
//	public void registerUser(String email) {
//		System.out.println("sddreee");
//		System.out.println("email " + email);
//
//	}
	
	@RequestMapping( value = "/loginUser", method = RequestMethod.POST)
	public void loginUser(String username, String password) {
		System.out.println("postvame " + username);
		System.out.println("pass " + password);

	}

	@RequestMapping( value = "/registerUser", method = RequestMethod.POST)
	public void registerUser(User user) {
		System.out.println(user);
		ls.registerUser(user);

	}


}
