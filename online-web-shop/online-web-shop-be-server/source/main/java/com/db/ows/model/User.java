package com.db.ows.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class User {

	private int userId;
	private String username;
	private String password;
	private String country;
	private String city;
	private String telephone;
	private String mail;
	private int user_state;
	private int profileOpenedcount;
	private int currentAdvertisementCount;
	private String registerDate;
	private String lastLoginDate;
	private List<Image> images;
	
	private MultipartFile image;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", country=" + country + ", city=" + city
				+ ", telephone=" + telephone + ", mail=" + mail + ", user_state=" + user_state + ", " + image + "]";
	}

	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getUser_state() {
		return user_state;
	}

	public void setUser_state(int user_state) {
		this.user_state = user_state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProfileOpenedcount() {
		return profileOpenedcount;
	}

	public void setProfileOpenedcount(int profileOpenedcount) {
		this.profileOpenedcount = profileOpenedcount;
	}

	public int getCurrentAdvertisementCount() {
		return currentAdvertisementCount;
	}

	public void setCurrentAdvertisementCount(int currentAdvertisementCount) {
		this.currentAdvertisementCount = currentAdvertisementCount;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}
	

}
