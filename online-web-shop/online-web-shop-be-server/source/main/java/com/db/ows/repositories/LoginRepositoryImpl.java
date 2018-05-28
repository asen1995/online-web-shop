package com.db.ows.repositories;

import static com.db.ows.model.CONSTANTS.ZERO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db.ows.model.Image;
import com.db.ows.model.ImageType;
import com.db.ows.model.User;
@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;
	
	@Autowired
	private ImageRepository imgr;
	
	public Integer registerUser(User user) {
		
		String seq = " select OWS_USERS_SEQ.nextval from dual ";

		Integer userId = jdbcTmpl.query(seq, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					return rs.getInt(1);
				}
				return null;
			}
		});

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OWS_USERS  (USER_ID,USERNAME,PASSWORD, COUNTRY,   CITY, TELEPHONE, MAIL )");
		sql.append(" VALUES (  :userId , :username , :password , :country , :city , :telephone , :mail )");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("country", user.getCountry());
		params.put("city", user.getCity());
		params.put("telephone", user.getTelephone());
		params.put("mail", user.getMail());

		jdbcTmpl.update(sql.toString(), params);
		
		return userId;

	}

	@Override
	public boolean userIsNotAlreadyRegistered(String username) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) users_with_name FROM OWS_USERS where USERNAME = :username ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);

		Integer findedUsers = 0;
		findedUsers = jdbcTmpl.queryForObject(sql.toString(), params, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet res, int arg1) throws SQLException {
				return res.getInt("users_with_name");
			}
		});

		return (findedUsers == ZERO ) ? true : false;

	}

	@Override
	public User getUser(String username, String password) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT USER_ID,USERNAME,PASSWORD,COUNTRY, CITY, TELEPHONE, MAIL, PROFILE_OPENED_COUNT, CURRENT_ADS, ");
		sql.append(" USER_STATUS, BANNED_IP, REG_DATE, Last_Login_Date From Ows_Users  ");
		sql.append(" where username = :username and password = :password ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);

		User user = null;
		user = jdbcTmpl.queryForObject(sql.toString(), params, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet res, int arg1) throws SQLException {
				User user = new User();
				user.setUserId(res.getInt("USER_ID"));
				user.setUsername(res.getString("USERNAME"));
				user.setPassword(res.getString("PASSWORD"));
				user.setCountry(res.getString("COUNTRY"));
				user.setCity(res.getString("CITY"));
				user.setTelephone(res.getString("TELEPHONE"));
				user.setMail(res.getString("MAIL"));
				user.setUser_state(res.getInt("USER_STATUS"));
				
				List<Image> imagesForUser = imgr.getImages(String.valueOf(user.getUserId()), ImageType.PROFILE.getType());
				
				user.setImages(imagesForUser);
				return user;
			}
		});

		return user;

	}

	@Override
	public boolean userExisting(String username, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) users_registered FROM OWS_USERS where USERNAME = :username AND  password = :password ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);

		Integer findedUsers = 0;
		findedUsers = jdbcTmpl.queryForObject(sql.toString(), params, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet res, int arg1) throws SQLException {
				return res.getInt("users_registered");
			}
		});

		return (findedUsers != ZERO) ? true : false;

	}

}
