package com.db.ows.repositories;

public interface AdminRepository {

	public boolean approveAdvertisement(String advertisementId);

	public boolean rejectAdvertisement(String advertisementId);

}
