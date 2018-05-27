package com.db.ows.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.db.ows.model.Advertisement;
import com.db.ows.services.AdvertisementService;
import com.db.ows.services.ImageService;

@RestController
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private AdvertisementService as;
	
	@Autowired
	private ImageService imgs;
	
	@RequestMapping(value = "/getAdvertisements", method = RequestMethod.GET)
	public List<Advertisement> getAdvertisements() {
		return as.getAdvertisements();
	}
	
	@RequestMapping(value = "/createAdvertisement", method = RequestMethod.POST)
	public boolean createAdvertisement(Advertisement advertisement, String userId, MultipartFile image) {
		Integer advId = as.createAdvertisement(advertisement, userId);
		imgs.saveImage(image, advId);
	
		return true;

	}

	
}
