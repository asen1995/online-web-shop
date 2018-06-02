package com.db.ows.model;

public enum LikeType {

	ADVERTISEMENT_LIKE("ADV"), IMAGE_LIKE("IMG"), COMMENT_LIKE("CMT");

	private String type;

	LikeType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
