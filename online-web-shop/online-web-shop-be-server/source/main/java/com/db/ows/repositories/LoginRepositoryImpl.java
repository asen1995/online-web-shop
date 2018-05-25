package com.db.ows.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.db.ows.model.User;
import static com.db.ows.model.CONSTANTS.*;
@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	public void registerUser(User user) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OWS_USERS  (USER_ID,USERNAME,PASSWORD, COUNTRY,   CITY, TELEPHONE, MAIL )");
		sql.append(" VALUES (  OWS_USERS_SEQ.nextval, :username , :password , :country , :city , :telephone , :mail )");		

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("country", user.getCountry());
		params.put("city", user.getCity());
		params.put("telephone", user.getTelephone());
		params.put("mail", user.getMail());

		jdbcTmpl.update(sql.toString(), params);

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

}
