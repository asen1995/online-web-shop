package com.db.ows.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.ows.model.Advertisement;
import com.db.ows.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/approveAdvertisement", method = RequestMethod.POST)
	public boolean approveAdvertisement(String advertisementId,String selectedGroupId) {
		return adminService.approveAdvertisement(advertisementId,selectedGroupId);
	}
	
	
	@RequestMapping(value = "/rejectAdvertisement", method = RequestMethod.POST)
	public boolean rejectAdvertisement(String advertisementId) {
		return adminService.rejectAdvertisement(advertisementId);
	}

}
