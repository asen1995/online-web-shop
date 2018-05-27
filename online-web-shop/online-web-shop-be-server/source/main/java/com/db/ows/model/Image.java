package com.db.ows.model;

import java.util.Arrays;

public class Image {

	private int imageId;
	private String imageInfo;
	private String imageType;
	private byte[] imageContent;
	private String dateUpload;
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getImageInfo() {
		return imageInfo;
	}
	public void setImageInfo(String imageInfo) {
		this.imageInfo = imageInfo;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public byte[] getImageContent() {
		return imageContent;
	}
	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}
	public String getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(String dateUpload) {
		this.dateUpload = dateUpload;
	}
	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageInfo=" + imageInfo + ", imageType=" + imageType + ", imageContent="
				+ Arrays.toString(imageContent) + ", dateUpload=" + dateUpload + "]";
	}

	
	
	
}
