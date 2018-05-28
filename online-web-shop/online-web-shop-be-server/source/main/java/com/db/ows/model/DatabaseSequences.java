package com.db.ows.model;

public enum DatabaseSequences {

	ADVERTISEMENTS_SEQ("Ows_Advertisements_Seq"), IMAGES_SEQ("OWS_IMAGES_SEQ");

	private String sequance;

	DatabaseSequences(String sequance) {
		this.sequance = sequance;
	}

	public String getSequance() {
		return sequance;
	}

}
