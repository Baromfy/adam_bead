package com.epam.universities.blog.view;

import java.util.*;
import java.io.*;

import com.epam.universities.blog.domain.*;

public class ConsoleUserView implements UserView{

	@Override
	public Credentials readCredentials() throws IOException {
		
		BufferedReader reader = new BufferedReader(
    	new InputStreamReader(System.in));
    	
    	System.out.print("===\nLogin name: ");  	
        String loginname = reader.readLine();
    	System.out.print("Password: ");
    	String pwd = reader.readLine();
		    		
    	Credentials cred = new Credentials();
    	cred.setLoginname(loginname);
    	cred.setPassword(pwd);
    	
		return cred;
	}

	@Override
	public void printWelcomeMessage(User user) {
		System.out.println("===\nWelcome to the Blogger Application " + user.getProfile().getName() + "!\n===");	
	}

	@Override
	public void printAllFollowers(List<User> followers) {
		System.out.println("===\nMy followers: ");
		for(int i = 0; i < followers.size(); i++) {
			System.out.println(followers.get(i).getProfile().getName());
		}
		System.out.println("===");
	}

	@Override
	public void printAllFollowed(List<User> followed) {
		System.out.println("===\nMy followings: ");
		for(int i = 0; i < followed.size(); i++) {
			System.out.println(followed.get(i).getProfile().getName());
		}
		System.out.println("===");		
	}

	@Override
	public String readUsernameToBeSearched() {
		System.out.println("===\nSearch user by full name: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String fullName = "";
		try {
			fullName = reader.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fullName;
	}

	@Override
	public void printUserProfile(User user, int numberOfFollowings, int numberOfFollowers) {
		if(user.getCredentials() != null) {
			System.out.println("Name: " + user.getProfile().getName());
			System.out.println("Quote: " + user.getProfile().getQuote());
			System.out.println("Location: " + user.getProfile().getLocation());
			System.out.println("Name: " + user.getProfile().getDateOfBirth());
			System.out.println("Join date: " + user.getProfile().getCreatedAt());
			System.out.println("Number of users followed by: " + numberOfFollowings);
			System.out.println("Number of followers: " + numberOfFollowers);
		}
	}

	@Override
	public boolean readIfUserWantsToFollow() {
		System.out.println("Press F to follow or M to got to menu... ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String followOrMenu = "";
		try {
			followOrMenu = reader.readLine().toUpperCase().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(followOrMenu.equals("F")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void printSuccessfulFollowMessage(User followed) {
		// TODO Auto-generated method stub
		
	}

}
