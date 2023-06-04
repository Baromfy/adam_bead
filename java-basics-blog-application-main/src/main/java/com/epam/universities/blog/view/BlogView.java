package com.epam.universities.blog.view;

import java.util.List;

import java.util.Map;

import com.epam.universities.blog.domain.Post;
import com.epam.universities.blog.domain.User;

public interface BlogView {

	public void printFollowed(Map<User, List<Post>> usersPosts);
	
	public Post readPost();
	
}
