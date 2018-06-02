package com.db.ows.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.db.ows.model.DatabaseSequences;
import com.db.ows.model.Like;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTmpl;

	@Autowired
	private SequenceRepository seqrepo;
	
	@Override
	public void initLikes(Integer refId, String type) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OWS_LIKES ( LIKES_ID, LIKES_COUNT,REFID, LIKE_TYPE )");
		sql.append(" VALUES ( OWS_LIKES_SEQ.nextval ,:initialLikeCount ,:refId , :likeType )");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("initialLikeCount", 0);
		params.put("refId", refId);
		params.put("likeType", type);

		jdbcTmpl.update(sql.toString(), params);

	}

	@Override
	public Like getLikes(int refId, String type) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT LIKES_ID, LIKES_COUNT FROM OWS_LIKES ");
		sql.append("where refid = :refId and like_type = :likeType ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("refId", refId);
		params.put("likeType", type);

		Like like = null;

		like = jdbcTmpl.queryForObject(sql.toString(), params, new RowMapper<Like>() {

			@Override
			public Like mapRow(ResultSet res, int arg1) throws SQLException {
				Like like = new Like();
				like.setLikeId(res.getInt("LIKES_ID"));
				like.setLikeCount(res.getInt("LIKES_COUNT"));
				return like;
			}
		});

		return like;

	}

	@Override
	public void addLike(Like like, String type) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE OWS_LIKES Set Likes_Count = Likes_Count+1 Where Likes_Id  = :likeId ");
		sql.append(" AND LIKE_TYPE   = :type");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeId", like.getLikeId());
		params.put("type", type);

		jdbcTmpl.update(sql.toString(), params);

	}

	@Override
	public void registerUserLike(Like like, String username) {
		
		
		Integer likeUsersId = seqrepo.getNextValueForSequence(DatabaseSequences.LIKES_USERS_SEQ.getSequance());

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OWS_LIKES_USERS (LIKES_USERS_ID,USERNAME, LIKES_ID) ");
		sql.append(" VALUES ( :likeUsersId ,:username, :likeId ) ");
  

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeUsersId", likeUsersId);
		params.put("username", username);
		params.put("likeId", like.getLikeId());
		
		jdbcTmpl.update(sql.toString(), params);

	}

}
