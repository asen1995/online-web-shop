package com.db.ows.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.db.ows.model.Advertisement;
import com.db.ows.model.ImageType;
import com.db.ows.model.Like;
import com.db.ows.model.User;
import com.db.ows.services.AdvertisementService;
import com.db.ows.services.ImageService;
import com.db.ows.services.LikeService;

@RestController
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private AdvertisementService as;

	@Autowired
	private ImageService imgs;

	@Autowired
	private LikeService ls;

	@RequestMapping(value = "/getAdvertisements", method = RequestMethod.GET)
	public List<Advertisement> getAdvertisements() {
		return as.getAdvertisements();
	}

	@RequestMapping(value = "/createAdvertisement", method = RequestMethod.POST)
	public boolean createAdvertisement(Advertisement advertisement, String userId, MultipartFile image) {
		Integer advId = as.createAdvertisement(advertisement, userId);
		imgs.saveImage(image, advId, ImageType.ADVERTISEMENT.getType());
		return true;

	}

	@RequestMapping(value = "/addLike", method = RequestMethod.POST)
	public boolean addLike(Like like, String username, String type) {

		ls.addLike(like, username, type);
		
		return true;

	}

}
