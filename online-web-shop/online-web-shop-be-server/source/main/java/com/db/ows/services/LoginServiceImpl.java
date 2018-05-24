package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.ows.model.User;
import com.db.ows.repositories.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository lr;

	@Override
	public void registerUser(User user) {
		lr.registerUser(user);
	}

}
