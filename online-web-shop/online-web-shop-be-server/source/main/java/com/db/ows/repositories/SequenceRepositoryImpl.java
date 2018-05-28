package com.db.ows.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceRepositoryImpl implements SequenceRepository {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Override
	public Integer getNextValueForSequence(String sequance) {

		String sql = "select " + sequance + ".nextval from dual ";

		Integer sequanceNextValue = jdbcTmpl.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					return rs.getInt(1);
				}
				return null;
			}
		});
		return sequanceNextValue;
	}

}
