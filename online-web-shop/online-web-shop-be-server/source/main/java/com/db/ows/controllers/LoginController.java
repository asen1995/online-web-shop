package com.db.ows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.ows.model.RegistrationState;
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

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public void loginUser(String username, String password) {	
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public RegistrationState registerUser(User user) {
	
		if (ls.userIsNotAlreadyRegistered(user.getUsername())) {
			ls.registerUser(user);
			System.out.println("registered");
			return RegistrationState.SUCCESS;
		} else {
			System.out.println("already registered");
			return RegistrationState.FAILED;
		}
	}

}
