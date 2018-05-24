package com.db.ows.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db.ows.model.User;

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
	
		System.out.println("lets try to register " + user);

		jdbcTmpl.update(sql.toString(), params);

	}

}
