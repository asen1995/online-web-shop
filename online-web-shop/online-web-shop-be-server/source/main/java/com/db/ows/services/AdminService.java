package com.db.ows.services;

public interface AdminService {

	public boolean approveAdvertisement(String advertisementId);

	public boolean rejectAdvertisement(String advertisementId);

}
