package com.db.ows.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.ows.model.Advertisement;
import com.db.ows.services.AdvertisementService;

@RestController
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private AdvertisementService as;
	
	@RequestMapping(value = "/getAdvertisements", method = RequestMethod.GET)
	public List<Advertisement> getAdvertisements() {
		return as.getAdvertisements();
	}

	
}
