package com.db.ows.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value = "/createAdvertisement", method = RequestMethod.POST)
	public boolean createAdvertisement(Advertisement advertisement, String userId, MultipartFile image) {

		as.createAdvertisement(advertisement, userId);
		return true;

	}

	
}
