package com.db.ows.model;

public class Advertisement {

	private String advertisementId;
	private String title;
	private String information;
	private String createDate;
	private String advertisementStatus;
	private User creator;
	private String price;
	
	public String getAdvertisementStatus() {
		return advertisementStatus;
	}

	public void setAdvertisementStatus(String advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}

	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Advertisement [advertisementId=" + advertisementId + ", title=" + title + ", information=" + information
				+ ", createDate=" + createDate + ", advertisementStatus=" + advertisementStatus + ", creator=" + creator
				+ ", price=" + price + "]";
	}
	

}
