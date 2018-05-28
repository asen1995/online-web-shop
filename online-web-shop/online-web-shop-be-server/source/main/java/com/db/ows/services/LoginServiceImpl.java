package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.ows.model.User;
import com.db.ows.repositories.LoginRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository lr;

	@Override
	public Integer registerUser(User user) {
		return lr.registerUser(user);
	}

	@Override
	public boolean userIsNotAlreadyRegistered(String username) {

		return lr.userIsNotAlreadyRegistered(username);
	}

	@Override
	public User getUser(String username, String password) {
		if(lr.userExisting(username,password)){			
			return lr.getUser(username, password);
		}
		return null;
	}

}
