package com.db.ows.model;

public enum AdvertisementStatus {

	APPROVED("APR"), WAITING_APPROVE("WAP") , DELETED("DEL") , REJECTED("RJT");

	private String status;

	AdvertisementStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
