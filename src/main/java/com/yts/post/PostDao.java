	package com.yts.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
	static final String ADD_POST = "INSERT INTO post(userId, name, content) VALUES(?,?,?)";
	static final String LIST_POST = "SELECT postId, userID, name, content, sweet, cdate FROM post ORDER BY postId desc LIMIT ?,?";
	static final String LIKE_POST = "UPDATE post SET sweet = sweet+1 WHERE postId=?";
	static final String GET_POST = "SELECT postId, userId, name, content, sweet, cdate FROM post WHERE postId=?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Post> postRowMapper = new BeanPropertyRowMapper<>(Post.class);
	
	public int addPost(Post post) {
		return jdbcTemplate.update(ADD_POST, post.getUserId(), post.getName(), post.getContent());
	}
	
	public List<Post> listPosts(int offset, int count){
		return jdbcTemplate.query(LIST_POST, postRowMapper, offset, count);
	}
	
	public int likePost(String postId) {
		return jdbcTemplate.update(LIKE_POST, postId);
	}
	
	public Post getPost(String postId) {
		return jdbcTemplate.queryForObject(GET_POST, postRowMapper, postId);
	}

}
