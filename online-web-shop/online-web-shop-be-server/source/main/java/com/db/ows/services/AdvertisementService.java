package com.db.ows.services;

import java.util.List;

import com.db.ows.model.Advertisement;

public interface AdvertisementService {
	public List<Advertisement> getAdvertisements();

	public Integer createAdvertisement(Advertisement advertisement, String userId);

	public List<Advertisement> getAdvertisements(String username);
}
