package com.db.ows.services;

import com.db.ows.model.Like;

public interface LikeService {

	void addLike(Like like, String username, String type);

}
