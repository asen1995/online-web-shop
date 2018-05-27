package com.db.ows.repositories;

import org.springframework.web.multipart.MultipartFile;

public interface ImageRepository {

	void saveImage(MultipartFile image, Integer advertisementId);

}
