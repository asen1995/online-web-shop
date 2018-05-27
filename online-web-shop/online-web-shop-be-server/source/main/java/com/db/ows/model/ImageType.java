package com.db.ows.model;

public enum ImageType {
	ADVERTISEMENT("A"), PROFILE("P");

	private String type;

	ImageType(String type) {
		this.type = type;

	}

	public String getType() {
		return type;
	}

}
