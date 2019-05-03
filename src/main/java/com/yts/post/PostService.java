package com.yts.post;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	@Autowired
	PostDao postDao;
	
	static final Logger logger = LogManager.getLogger();
	
	public void addPost() {
		Post post = new Post();
		post.setUserId("14");
		post.setName("윤태식");
		post.setContent("중간고사 끝!");
		postDao.addPost(post);
		logger.debug("글을 등록했습니다.");
	}
	
	public void listPosts() {
		List<Post> postList = postDao.listPosts(0, 50);
		logger.debug(postList);
	}
	
	public void likesPost() {
		final String postId = "91";
		postDao.likePost(postId);
		
		Post post = postDao.getPost(postId);
		logger.debug(post);
	}
}
