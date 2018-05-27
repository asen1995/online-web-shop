package com.db.ows.repositories;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.db.ows.model.ImageType;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Override
	public void saveImage(MultipartFile image) {

		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT	INTO OWS_IMAGES  ( IMAGE_ID, IMAGE_INFO, IMAGE_TYPE, REFID,IMAGE_CONTENT ) ");
		sql.append(" VALUES ( OWS_IMAGES_SEQ.nextval ,:imageinfo,  :imageType, ");
		sql.append(" :refId , :imageBytes  ) ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imageinfo", "infosnimka");
		params.put("imageType", ImageType.ADVERTISEMENT.getType());
		params.put("refId", 2);
		
		byte[] imageBytes = null;
		try {
			imageBytes = image.getBytes();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
		params.put("imageBytes", imageBytes );		

		jdbcTmpl.update(sql.toString(), params);
		
	}

}
