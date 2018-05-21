package com.db.ows.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

	@Autowired
	JdbcTemplate jdbcTmpl;
	
	public void registerUser() {
		System.out.println("re");
		System.out.println(jdbcTmpl);
		System.out.println(jdbcTmpl != null);
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ows_users ");
		sql.append("(USERNAME, PASSWORD, COUNTRY, USER_STATUS) ");
		sql.append("values(:username, :password, :country, 1) ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "petur");
		params.put("password", "otposcih");
		params.put("country", "rre");	

		jdbcTmpl.update(sql.toString(), params);
		System.out.println("done");
	}

}
