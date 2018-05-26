package com.db.ows.model;

public class Advertisement {

	private int advertisementId;
	private String title;
	private String information;
	private String createDate;
	private String advertisementStatus;
	private User creator;

	public String getAdvertisementStatus() {
		return advertisementStatus;
	}

	public void setAdvertisementStatus(String advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}

	public int getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
