package com.epam.universities.blog.service;

import java.util.List;

import java.util.Map;
import com.epam.universities.blog.domain.*;
import com.epam.universities.blog.persistence.DataStore;


public class DefaultBlogService implements BlogService{

	public DefaultBlogService(DataStore dataStore) {
		
	}

	@Override
	public Map<User, List<Post>> getPostsOfFollowedUsers(User user) {
		
		return null;
	}

	@Override
	public void createPost(User user, Post post) {
		
		
	}
}
