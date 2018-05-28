package com.db.ows.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public void saveImage(MultipartFile image, Integer refId, String type);
}
