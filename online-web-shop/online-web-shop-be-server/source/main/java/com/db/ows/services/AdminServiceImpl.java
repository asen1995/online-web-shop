package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.ows.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository ar;
	
	@Override
	public boolean approveAdvertisement(String advertisementId) {		
		return ar.approveAdvertisement(advertisementId);
	}

	@Override
	public boolean rejectAdvertisement(String advertisementId) {
		return ar.rejectAdvertisement(advertisementId);
	}

}
