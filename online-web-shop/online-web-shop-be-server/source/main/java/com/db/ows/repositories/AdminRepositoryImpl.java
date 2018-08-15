package com.db.ows.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db.ows.model.AdvertisementStatus;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Override
	public boolean approveAdvertisement(String advertisementId, String selectedGroupId) {
		//changeStatusOfAdvertisement(advertisementId, AdvertisementStatus.APPROVED.getStatus());
		
		StringBuilder sql = new StringBuilder();

		sql.append("Update Ows_Advertisements ");
		sql.append("Set Advertisement_Status = :statusApprove , Advertisement_Group_Id = :selectedGroupId ");
		sql.append(" WHERE ADVERTISEMENT_ID     = :advertisementId ");

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("advertisementId", advertisementId);
		params.put("statusApprove", AdvertisementStatus.APPROVED.getStatus());
		params.put("selectedGroupId", selectedGroupId);
		jdbcTmpl.update(sql.toString(), params);
		
		return true;
	}
	
	@Override
	public boolean rejectAdvertisement(String advertisementId) {
		changeStatusOfAdvertisement(advertisementId, AdvertisementStatus.REJECTED.getStatus());		
		return false;
	}


	private void changeStatusOfAdvertisement(String advertisementId, String statusApprove) {
		StringBuilder sql = new StringBuilder();

		sql.append("Update Ows_Advertisements ");
		sql.append("Set Advertisement_Status = :statusApprove ");
		sql.append(" WHERE ADVERTISEMENT_ID     = :advertisementId ");

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("advertisementId", advertisementId);
		params.put("statusApprove", statusApprove);

		jdbcTmpl.update(sql.toString(), params);

	}

	
}
