package com.db.ows.repositories;

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

import com.db.ows.model.Advertisement;
import com.db.ows.model.User;

@Repository
public class AdvertisementRepositoryImpl implements AdvertisementRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Override
	public List<Advertisement> getAdvertisements() {

		StringBuilder sql = new StringBuilder();
		sql.append("Select Ad.Advertisement_Id, Ad.Title,  Ad.Information,  Ad.Create_Date, ");
		sql.append(
				" Us.user_id , Us.username, Us.Country , Us.city, Us.Telephone , Us.Mail, Us.Profile_Opened_Count, Us.Current_Ads, ");
		sql.append(" Us.Reg_Date , Us.Last_Login_Date ");
		sql.append(" From Ows_Advertisements Ad ");
		sql.append(" Left Join  Ows_Users Us ");
		sql.append(" on Ad.CREATOR_USER_ID = Us.user_id ");

		List<Advertisement> advertisements = jdbcTmpl.query(sql.toString(),
				new ResultSetExtractor<List<Advertisement>>() {

					@Override
					public List<Advertisement> extractData(ResultSet res) throws SQLException, DataAccessException {
						List<Advertisement> advertisements = new ArrayList<Advertisement>();

						while (res.next()) {
						
							Advertisement advertisement = new Advertisement();
							advertisement.setAdvertisementId(res.getInt("Advertisement_Id"));
							advertisement.setTitle(res.getString("Title"));
							advertisement.setInformation(res.getString("Information"));
							advertisement.setCreateDate(res.getString("Create_Date"));

							User creatorOfAdvertisement = new User();
							
							creatorOfAdvertisement.setUserId(res.getInt("user_id"));
							creatorOfAdvertisement.setUsername(res.getString("username"));
							creatorOfAdvertisement.setCountry(res.getString("Country"));
							creatorOfAdvertisement.setCity(res.getString("city"));
							creatorOfAdvertisement.setTelephone(res.getString("Telephone"));
							creatorOfAdvertisement.setMail(res.getString("Mail"));
							creatorOfAdvertisement.setProfileOpenedcount(res.getInt("Profile_Opened_Count"));
							creatorOfAdvertisement.setCurrentAdvertisementCount(res.getInt("Current_Ads"));
							creatorOfAdvertisement.setRegisterDate(res.getString("Reg_Date"));
							creatorOfAdvertisement.setLastLoginDate(res.getString("Last_Login_Date"));

							advertisement.setCreator(creatorOfAdvertisement);

							advertisements.add(advertisement);
						}
						return advertisements;
					}
				});
	
		return advertisements;
	}

}
