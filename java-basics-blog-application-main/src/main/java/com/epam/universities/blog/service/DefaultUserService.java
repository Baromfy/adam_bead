package com.epam.universities.blog.service;

import java.util.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.epam.universities.blog.domain.*;
import com.epam.universities.blog.persistence.*;


public class DefaultUserService implements UserService {

	public List<User> users = new ArrayList<User>();
	public List<Following> followings = new ArrayList<Following>();
	public List<User> follows = new ArrayList<User>();
	public List<User> followed = new ArrayList<User>();
	
	Path path = Paths.get("D:\\JAVA_Git\\java-basics-blog-application\\data");
	DataStore ds = new FileDataStore(path);
	
	
	public DefaultUserService(DataStore dataStore) throws FileNotFoundException {	
		dataStore.load();	
		users = dataStore.getAllUsers();
		followings = dataStore.getAllFollowings();
	}

	@Override
//	public User authenticate(Credentials credentials) throws AuthenticationException {
//		User ok = new User();
//		if(users != null) {
//			boolean hasValidUser = false;
//			for(int i = 0; i < users.size(); i++) {
//				ok = users.get(i);
//				if(ok.getCredentials().getLoginname().trim().equals(credentials.getLoginname().trim()) 
//				&& ok.getCredentials().getPassword().trim().equals(credentials.getPassword().trim())) 
//				{
//					System.out.println("Sikerült belépni");
//					hasValidUser = true;
//					break;
//				} 
//			}
//			if(!hasValidUser) {
//				throw new AuthenticationException("Invalid loginname and/or password!");
//			}
//		} 
//		return ok;
//	}

	
	public User authenticate(Credentials credentials) throws AuthenticationException {
        User ok = null;
        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (user.getCredentials().getLoginname().trim().equals(credentials.getLoginname().trim()) &&
                        user.getCredentials().getPassword().trim().equals(credentials.getPassword().trim())) {
//                    System.out.println("Sikerült belépni");
                    ok = user;
                    break;
                }
            }
            if (ok == null) {
                throw new AuthenticationException("Invalid loginname and/or password!");
            }
        } else {
            throw new AuthenticationException("No users found!");
        }
        return ok;
    }

	
	
	
	@Override
	public Optional<User> searchUser(String fullName) {	
		for(int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getProfile().getName());
			if(users.get(i).getProfile().getName().equals(fullName)) {
				return Optional.of(users.get(i));
			}
		}
		return null;
	}

	@Override
	public List<User> getAllFollowers(User user) {
		if(followings != null) {
			for(int i = 0; i < followings.size(); i++) {
				if(followings.get(i).getFollowed().getId() == user.getId()) {
					follows.add(followings.get(i).getFollower());
				}
			}
		}
		return follows;
	}

	@Override
	public List<User> getAllFollowed(User user) {
		if(followings != null) {
			for(int i = 0; i < followings.size(); i++) {
				if(followings.get(i).getFollower().getId() == user.getId()) {
					followed.add(followings.get(i).getFollowed());
				}
			}
		}
		return followed;
	}

	@Override
	public void follow(User follower, User followed) {
		String csvFilePath = "D:\\JAVA_Git\\java-basics-blog-application\\data\\followings.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {                 //TO-DO: IllegalArgument ex.
            writer.append("\n" + follower.getId() + "," + followed.getId() + "," + LocalDate.now().toString().replace('-', '.') + ".");
            writer.close();
        } catch (IOException e) {
            System.err.println("Hiba történt a CSV-fájl írása közben: " + e.getMessage());
        }		
	}
	
//	public static void main2(String[] args) throws FileNotFoundException {
//		
//		Path path = Paths.get("D:\\JAVA_Git\\java-basics-blog-application\\data");
//		DataStore ds = new FileDataStore(path);
//		DefaultUserService dus = new DefaultUserService(ds);
//		
//		
//		System.out.println(dus.users.size());
//	}
}
