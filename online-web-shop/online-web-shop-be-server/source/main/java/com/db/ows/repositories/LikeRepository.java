package com.db.ows.repositories;

import com.db.ows.model.Like;

public interface LikeRepository {

	public void initLikes(Integer refId, String type);

	public Like getLikes(int refId, String type);

	public void addLike(Like like, String type);

	public void registerUserLike(Like like, String username);

}
