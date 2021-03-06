package com.db.ows.repositories;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.db.ows.model.DatabaseSequences;
import com.db.ows.model.Image;
import com.db.ows.model.Like;
import com.db.ows.model.LikeType;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Autowired
	private LikeRepository lkr;
	
	@Autowired
	private SequenceRepository seqrep;
	
	@Override
	public void saveImage(MultipartFile image, Integer refId , String type) {

		
		Integer imgId = seqrep.getNextValueForSequence(DatabaseSequences.IMAGES_SEQ.getSequance());		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT	INTO OWS_IMAGES  ( IMAGE_ID, IMAGE_INFO, IMAGE_TYPE, REFID,IMAGE_CONTENT ) ");
		sql.append(" VALUES ( :imgId ,:imageinfo,  :imageType, ");
		sql.append(" :refId , :imageBytes  ) ");

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("imgId", imgId);
		params.put("imageinfo", "infosnimka");
		params.put("imageType", type);
		params.put("refId", refId);
		
		byte[] imageBytes = null;
		try {
			imageBytes = image.getBytes();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
		params.put("imageBytes", imageBytes );		

		jdbcTmpl.update(sql.toString(), params);
		
		lkr.initLikes(imgId, LikeType.IMAGE_LIKE.getType());
	}
	
	public List<Image> getImages(String refId,String imageType) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT IMAGE_ID, IMAGE_INFO, IMAGE_TYPE,IMAGE_CONTENT,Date_Upload ");
		sql.append("From Ows_Images where REFID = :refId and image_type = :imageType ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("refId", refId);
		params.put("imageType", imageType);

		List<Image> imagesForAdvertisement = jdbcTmpl.query(sql.toString(), params, new ResultSetExtractor<List<Image>>() {

			@Override
			public List<Image> extractData(ResultSet res) throws SQLException, DataAccessException {
				List<Image> imagesForAdvertisement = new ArrayList<Image>();

				while (res.next()) {
					Image image = new Image();
					image.setImageId(res.getInt("IMAGE_ID"));
					image.setImageInfo(res.getString("IMAGE_INFO"));
					image.setImageType(res.getString("IMAGE_TYPE"));
					image.setImageContent(res.getBytes("IMAGE_CONTENT"));
					image.setDateUpload(res.getString("Date_Upload"));
					
					Like likes = lkr.getLikes(image.getImageId(),LikeType.IMAGE_LIKE.getType());
					
					image.setLikes(likes);
					
					imagesForAdvertisement.add(image);
				}
				return imagesForAdvertisement;
			}

		});

		return imagesForAdvertisement;

	}

}
