package com.epam.universities.blog.service;

import java.util.List;

import java.util.Map;

import com.epam.universities.blog.domain.*;

public interface BlogService {
	
	public Map<User, List<Post>> getPostsOfFollowedUsers(User user);
	
	public void createPost(User user, Post post);
}
