package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.ows.model.Like;
import com.db.ows.repositories.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository lr;

	@Override
	public void addLike(Like like, String username, String type) {
		lr.registerUserLike(like,username);
		lr.addLike(like, type);

	}
}
