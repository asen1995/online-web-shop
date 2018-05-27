package com.db.ows.services;

import java.util.List;

import com.db.ows.model.Advertisement;

public interface AdvertisementService {
	public List<Advertisement> getAdvertisements();

	public void createAdvertisement(Advertisement advertisement, String userId);
}
