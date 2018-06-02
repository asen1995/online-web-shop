package com.db.ows.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.ows.model.Advertisement;
import com.db.ows.repositories.AdvertisementRepository;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementRepository ar;
	
	@Override
	public List<Advertisement> getAdvertisements() {		
		return ar.getAdvertisements();
	}
	@Override
	public List<Advertisement> getAdvertisements(String username) {		
		return ar.getAdvertisements(username);
	}
	@Override
	public Integer createAdvertisement(Advertisement advertisement, String userId) {
		return ar.createAdvertisement(advertisement, userId);
	}

	

}
