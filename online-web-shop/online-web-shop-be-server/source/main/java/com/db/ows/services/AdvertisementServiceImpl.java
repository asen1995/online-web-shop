package com.db.ows.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.ows.model.Advertisement;
import com.db.ows.repositories.AdvertisementRepository;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementRepository ar;
	
	@Override
	public List<Advertisement> getAdvertisements() {		
		return ar.getAdvertisements();
	}

	@Override
	public void createAdvertisement(Advertisement advertisement, String userId) {
		ar.createAdvertisement(advertisement, userId);
	}

}
