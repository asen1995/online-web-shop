package com.db.ows.services;

public interface AdminService {

	public boolean approveAdvertisement(String advertisementId, String selectedGroupId);

	public boolean rejectAdvertisement(String advertisementId);

}
