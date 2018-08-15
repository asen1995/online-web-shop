package com.db.ows.repositories;

public interface AdminRepository {

	public boolean approveAdvertisement(String advertisementId, String selectedGroupId);

	public boolean rejectAdvertisement(String advertisementId);

}
