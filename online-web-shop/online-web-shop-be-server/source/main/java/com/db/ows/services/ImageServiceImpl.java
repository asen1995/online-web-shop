package com.db.ows.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.db.ows.repositories.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imgr;

	@Override
	public void saveImage(MultipartFile image, Integer refId, String type) {
		imgr.saveImage(image,refId, type);
	}

}
