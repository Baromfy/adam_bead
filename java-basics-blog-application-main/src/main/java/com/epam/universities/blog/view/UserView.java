package com.epam.universities.blog.view;

import java.io.IOException;
import java.util.List;


import com.epam.universities.blog.domain.Credentials;
import com.epam.universities.blog.domain.User;

public interface UserView {

	public Credentials readCredentials() throws IOException;
	
	public void printWelcomeMessage(User user);
	
	public void printAllFollowers(List<User> followers);
	
	public void printAllFollowed(List<User> followed);
	
	public String readUsernameToBeSearched();
	
	public void printUserProfile(User user, int numberOfFollowings, int numberOfFollowers);
	
	public boolean readIfUserWantsToFollow();
	
	public void printSuccessfulFollowMessage(User followed);
 }
