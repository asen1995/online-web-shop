package com.db.ows.repositories;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.db.ows.model.Image;

public interface ImageRepository {

	public	void saveImage(MultipartFile image, Integer refId, String type);
	
	public List<Image> getImages(String refId,String imageType);

}
