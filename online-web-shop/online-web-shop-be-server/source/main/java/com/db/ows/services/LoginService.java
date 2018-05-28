package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.ows.model.User;
import com.db.ows.repositories.LoginRepository;

public interface LoginService {

	public Integer registerUser(User user);

	public boolean userIsNotAlreadyRegistered(String username);

	public User getUser(String username, String password);

}
