package com.epam.universities.blog.service;

import java.util.List;

import java.util.Optional;

import com.epam.universities.blog.domain.*;

public interface UserService {

	public User authenticate(Credentials credentials);
	
	public Optional<User> searchUser(String fullName);
	
	public List<User> getAllFollowers(User user);
	
	public List<User> getAllFollowed(User user);
	
	public void follow(User follower, User followed);
}
