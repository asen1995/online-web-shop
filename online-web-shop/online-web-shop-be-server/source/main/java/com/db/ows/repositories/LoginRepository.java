package com.db.ows.repositories;

import com.db.ows.model.User;

public interface LoginRepository {
	
	public Integer registerUser(User user);

	public boolean userIsNotAlreadyRegistered(String username);

	public User getUser(String username, String password);

	public boolean userExisting(String username, String password);
}
