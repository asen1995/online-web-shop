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

import com.db.ows.model.AdverisementGroup;
import com.db.ows.model.Advertisement;
import com.db.ows.model.AdvertisementStatus;
import com.db.ows.model.DatabaseSequences;
import com.db.ows.model.Image;
import com.db.ows.model.ImageType;
import com.db.ows.model.Like;
import com.db.ows.model.LikeType;
import com.db.ows.model.User;

@Repository
public class AdvertisementRepositoryImpl implements AdvertisementRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Autowired
	private ImageRepository imgr;

	@Autowired
	private LikeRepository lkr;

	@Autowired
	private SequenceRepository seqrepo;
	
	@Override
	public List<Advertisement> getAdvertisements() {
		return getAdvertisementsWithStatus(AdvertisementStatus.APPROVED);
	}

	@Override
	public Integer createAdvertisement(Advertisement advertisement, String userId) {

			
		Integer advId = seqrepo.getNextValueForSequence(DatabaseSequences.ADVERTISEMENTS_SEQ.getSequance());

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OWS_ADVERTISEMENTS  ( ADVERTISEMENT_ID, TITLE,INFORMATION, ");
		sql.append(" ADVERTISEMENT_STATUS, CREATOR_USER_ID ,PRICE ) ");
		sql.append("VALUES  ( :advId , :title,  " + ":information ,  :advertisementStatus , :cre_user_id , :price ) ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("advId", advId);
		params.put("title", advertisement.getTitle());
		params.put("information", advertisement.getInformation());
		params.put("advertisementStatus", AdvertisementStatus.WAITING_APPROVE.getStatus());
		params.put("cre_user_id", userId);
		params.put("price", advertisement.getPrice());

		jdbcTmpl.update(sql.toString(), params);

		lkr.initLikes(advId, LikeType.ADVERTISEMENT_LIKE.getType());

		return advId;
	}

	@Override
	public List<Advertisement> getAdvertisements(String username) {

		StringBuilder sql = new StringBuilder();
		sql.append("Select Ad.Advertisement_Id, Ad.Title,  Ad.Information,  Ad.Create_Date, Ad.price , ");
		sql.append(
				" Us.user_id , Us.username, Us.Country , Us.city, Us.Telephone , Us.Mail, Us.Profile_Opened_Count, Us.Current_Ads, ");
		sql.append(" Us.Reg_Date , Us.Last_Login_Date , Lk.Likes_Id, Lk.Likes_Count , lkus.username USER_LIKE_THIS  ");
		sql.append(" From Ows_Advertisements Ad ");
		sql.append(" Left Join  Ows_Users Us ");
		sql.append(" on Ad.CREATOR_USER_ID = Us.user_id ");
		sql.append(" Left Join Ows_Likes Lk ");
		sql.append("  on Lk.Refid = ad.Advertisement_Id ");		
		sql.append("   Left Join Ows_Likes_Users Lkus ");
		sql.append("    on lk.LIKES_ID =  Lkus.LIKES_ID ");
		sql.append(" order by Ad.Create_Date desc ");
		
		List<Advertisement> advertisements = jdbcTmpl.query(sql.toString(),
				new ResultSetExtractor<List<Advertisement>>() {

					@Override
					public List<Advertisement> extractData(ResultSet res) throws SQLException, DataAccessException {
						List<Advertisement> advertisements = new ArrayList<Advertisement>();

						while (res.next()) {

							Advertisement advertisement = new Advertisement();
							advertisement.setAdvertisementId(res.getString("Advertisement_Id"));
							advertisement.setTitle(res.getString("Title"));
							advertisement.setInformation(res.getString("Information"));
							advertisement.setCreateDate(res.getString("Create_Date"));
							advertisement.setPrice(res.getString("price"));
							advertisement.setLoggedUserLike(res.getString("USER_LIKE_THIS") != null );
							
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

							/*Like likes = lkr.getLikes(Integer.parseInt(advertisement.getAdvertisementId()),
									LikeType.ADVERTISEMENT_LIKE.getType());*/
							Like likes = new Like();
							likes.setLikeId(res.getInt("Likes_Id"));
							likes.setLikeCount(res.getInt("Likes_Count"));

							advertisement.setLikes(likes);

							List<Image> imagesForAdvertisement = imgr.getImages(advertisement.getAdvertisementId(),
									ImageType.ADVERTISEMENT.getType());

							advertisement.setImages(imagesForAdvertisement);

							advertisements.add(advertisement);
						}
						return advertisements;
					}

				});

		return advertisements;
	}

	@Override
	public List<Advertisement> getAdvertisementsForApprove() {		
		return getAdvertisementsWithStatus(AdvertisementStatus.WAITING_APPROVE);
	}	
	
	private List<Advertisement> getAdvertisementsWithStatus(AdvertisementStatus status) {

		StringBuilder sql = new StringBuilder();
		sql.append("Select Ad.Advertisement_Id, Ad.Title,  Ad.Information,  Ad.Create_Date, Ad.price , ");
		sql.append(
				" Us.user_id , Us.username, Us.Country , Us.city, Us.Telephone , Us.Mail, Us.Profile_Opened_Count, Us.Current_Ads, ");
		sql.append(" Us.Reg_Date , Us.Last_Login_Date , Lk.Likes_Id, Lk.Likes_Count ");
		sql.append(" From Ows_Advertisements Ad ");
		sql.append(" Left Join  Ows_Users Us ");
		sql.append(" on Ad.CREATOR_USER_ID = Us.user_id ");
		sql.append(" Left Join Ows_Likes Lk ");
		sql.append("  on Lk.Refid = ad.Advertisement_Id ");
		sql.append(" where Ad.ADVERTISEMENT_STATUS = :status ");
		sql.append(" order by Ad.Create_Date desc ");
		

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("status", status.getStatus());

		List<Advertisement> advertisements = jdbcTmpl.query(sql.toString(), params,
				new ResultSetExtractor<List<Advertisement>>() {

					@Override
					public List<Advertisement> extractData(ResultSet res) throws SQLException, DataAccessException {
						List<Advertisement> advertisements = new ArrayList<Advertisement>();

						while (res.next()) {

							Advertisement advertisement = new Advertisement();
							advertisement.setAdvertisementId(res.getString("Advertisement_Id"));
							advertisement.setTitle(res.getString("Title"));
							advertisement.setInformation(res.getString("Information"));
							advertisement.setCreateDate(res.getString("Create_Date"));
							advertisement.setPrice(res.getString("price"));

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

							/*
							 * Like likes =
							 * lkr.getLikes(Integer.parseInt(advertisement.
							 * getAdvertisementId()),
							 * LikeType.ADVERTISEMENT_LIKE.getType());
							 */
							Like likes = new Like();
							likes.setLikeId(res.getInt("Likes_Id"));
							likes.setLikeCount(res.getInt("Likes_Count"));

							advertisement.setLikes(likes);

							List<Image> imagesForAdvertisement = imgr.getImages(advertisement.getAdvertisementId(),
									ImageType.ADVERTISEMENT.getType());

							advertisement.setImages(imagesForAdvertisement);

							advertisements.add(advertisement);
						}
						return advertisements;
					}

				});

		return advertisements;

	}

	@Override
	public boolean createAdvertisementGroup(String groupName) {	
		
		Integer groupId = seqrepo.getNextValueForSequence(DatabaseSequences.ADVERTISEMENT_GROUP_SEQ.getSequance());	
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO OWS_ADVERTISEMENTS_GROUPS ( ADVERTISEMENT_GROUP_ID, GROUP_NAME ) VALUES ( ");
		sql.append("   :groupId ,  :groupName ) ");
	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("groupName", groupName);		

		jdbcTmpl.update(sql.toString(), params);		
		return true;
	}

	@Override
	public List<AdverisementGroup> getExistingGroups() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ADVERTISEMENT_GROUP_ID, GROUP_NAME FROM OWS_ADVERTISEMENTS_GROUPS ;");

		List<AdverisementGroup> advertisementsGroups = jdbcTmpl.query(sql.toString(),
				new ResultSetExtractor<List<AdverisementGroup>>() {

					@Override
					public List<AdverisementGroup> extractData(ResultSet res) throws SQLException, DataAccessException {
						List<AdverisementGroup> advertisementsGroups = new ArrayList<AdverisementGroup>();

						while (res.next()) {

							AdverisementGroup advertisementsGroup = new AdverisementGroup();
							advertisementsGroup.setGroupId(res.getInt("ADVERTISEMENT_GROUP_ID"));
							advertisementsGroup.setGroupName(res.getString("GROUP_NAME"));

							advertisementsGroups.add(advertisementsGroup);
						}
						return advertisementsGroups;
					}

				});

		return advertisementsGroups;

	}

	@Override
	public List<Advertisement> getAdvertisementByGroup(String groupId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("Select Ad.Advertisement_Id, Ad.Title,  Ad.Information,  Ad.Create_Date, Ad.price , Ad.Advertisement_Group_Id, ");
		sql.append(
				" Us.user_id , Us.username, Us.Country , Us.city, Us.Telephone , Us.Mail, Us.Profile_Opened_Count, Us.Current_Ads, ");
		sql.append(" Us.Reg_Date , Us.Last_Login_Date , Lk.Likes_Id, Lk.Likes_Count ");
		sql.append(" From Ows_Advertisements Ad ");
		sql.append(" Left Join  Ows_Users Us ");
		sql.append(" on Ad.CREATOR_USER_ID = Us.user_id ");
		sql.append(" Left Join Ows_Likes Lk ");
		sql.append("  on Lk.Refid = ad.Advertisement_Id ");
		sql.append(" where Ad.ADVERTISEMENT_STATUS = :status And Ad.Advertisement_Group_Id = :groupId  ");
		sql.append(" order by Ad.Create_Date desc ");
		

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("status", AdvertisementStatus.APPROVED.getStatus());
		params.put("groupId", groupId);

		List<Advertisement> advertisements = jdbcTmpl.query(sql.toString(), params,
				new ResultSetExtractor<List<Advertisement>>() {

					@Override
					public List<Advertisement> extractData(ResultSet res) throws SQLException, DataAccessException {
						List<Advertisement> advertisements = new ArrayList<Advertisement>();

						while (res.next()) {

							Advertisement advertisement = new Advertisement();
							advertisement.setAdvertisementId(res.getString("Advertisement_Id"));
							advertisement.setTitle(res.getString("Title"));
							advertisement.setInformation(res.getString("Information"));
							advertisement.setCreateDate(res.getString("Create_Date"));
							advertisement.setPrice(res.getString("price"));

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

							/*
							 * Like likes =
							 * lkr.getLikes(Integer.parseInt(advertisement.
							 * getAdvertisementId()),
							 * LikeType.ADVERTISEMENT_LIKE.getType());
							 */
							Like likes = new Like();
							likes.setLikeId(res.getInt("Likes_Id"));
							likes.setLikeCount(res.getInt("Likes_Count"));

							advertisement.setLikes(likes);

							List<Image> imagesForAdvertisement = imgr.getImages(advertisement.getAdvertisementId(),
									ImageType.ADVERTISEMENT.getType());

							advertisement.setImages(imagesForAdvertisement);

							advertisements.add(advertisement);
						}
						return advertisements;
					}

				});

		return advertisements;

	}
	
	
}
