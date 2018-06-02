package com.db.ows.model;

public enum DatabaseSequences {

	ADVERTISEMENTS_SEQ("Ows_Advertisements_Seq"), IMAGES_SEQ("OWS_IMAGES_SEQ"), LIKES_USERS_SEQ("Ows_Likes_Users_Seq");

	private String sequance;

	DatabaseSequences(String sequance) {
		this.sequance = sequance;
	}

	public String getSequance() {
		return sequance;
	}

}
