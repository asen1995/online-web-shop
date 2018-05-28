package com.db.ows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.db.ows.model.ImageType;
import com.db.ows.model.RegistrationState;
import com.db.ows.model.User;
import com.db.ows.services.ImageService;
import com.db.ows.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	
	
	@Autowired
	private LoginService ls;
	
	@Autowired
	private ImageService imgs;

	@RequestMapping(value = "/loginUser", method = RequestMethod.GET)
	public User loginUser(String username, String password) {
		return ls.getUser(username,password);
	}

	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public RegistrationState registerUser(User user) 
	{
		if (ls.userIsNotAlreadyRegistered(user.getUsername())) {
			Integer userId = ls.registerUser(user);

			if (user.getImage() != null) {
				imgs.saveImage(user.getImage(), userId, ImageType.PROFILE.getType());
			}

			return RegistrationState.SUCCESS;
		} else {
			
			return RegistrationState.FAILED;
		}
	}

}
