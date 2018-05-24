package com.db.ows.model;

public class User {

	private String username;
	private String password;
	private String country;
	private String city;
	private String telephone;
	private String mail;
	private int user_state;
	
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", country=" + country + ", city=" + city
				+ ", telephone=" + telephone + ", mail=" + mail + ", user_state=" + user_state + "]";
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
	
	
	
	
	
	
}
