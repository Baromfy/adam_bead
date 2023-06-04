package com.epam.universities.blog.persistence;

import java.io.*;


import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.epam.universities.blog.domain.*;

public class FileDataStore implements DataStore {

	private Path path;
	private List<User> users;
	private List<Following> followings;

	public FileDataStore(Path path) {
		this.path = path;
		this.users = new ArrayList<>();
	    this.followings = new ArrayList<>();
	}

	public Path getPath() {
		return path;
	}
	
	@Override
	public void load() throws FileNotFoundException {	
		String usersFilePath = "D:\\JAVA_Git\\java-basics-blog-application\\data\\users.csv";
		String followingsFilePath = "D:\\JAVA_Git\\java-basics-blog-application\\data\\followings.csv";
		String postsFilePath = "D:\\JAVA_Git\\java-basics-blog-application\\data\\posts.txt";


    	try (BufferedReader ubr = new BufferedReader(new FileReader(usersFilePath))) {
    		String line = ubr.readLine();
    		
    		do {
    			User u = new User();
    			
    			String tomb[] = new String[8];
    			tomb = line.split(",");
    			
    			u.setId(Integer.parseInt(tomb[0]));
    			
    			Credentials c = new Credentials();
    			c.setLoginname(tomb[1]);
    			c.setPassword(tomb[2]);
    			
    			Profile p = new Profile();
    			p.setName(tomb[3]);
    			p.setDateOfBirth(LocalDate.parse((tomb[4]).replace('.','-').substring(0,10)));
    			p.setLocation(tomb[5]);
    			p.setQuote(tomb[6]);
    			p.setCreatedAt(LocalDate.parse((tomb[7]).replace('.','-').substring(0,10)));
    			
    			u.setCredentials(c);
    			u.setProfile(p);	
    			
    			users.add(u);
    			
    			line = ubr.readLine();
    			
    		} while(line != null);
			ubr.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
    	
    	List<Post> posts = new ArrayList<Post>();		
		
		try (BufferedReader pbr = new BufferedReader(new FileReader(postsFilePath))) {
			String postLine = pbr.readLine();
			do {
				Post post = new Post();
				post.setId(Integer.parseInt(postLine.substring(4)));
				postLine = pbr.readLine();
				for(int i = 0; i < users.size(); i++) {
					if(users.get(i).getId() == Integer.parseInt(postLine.substring(8))) {
						post.setAuthor(users.get(i));
						break;
					}
				}
				
				postLine = pbr.readLine();  					
				post.setCreatedAt(LocalDateTime.parse(postLine.substring(10).replace('.','-').substring(0,10).concat(postLine.substring(10).replace(' ','T').substring(11))));               //Még nem jó a formátum
				postLine = pbr.readLine();
				post.setTitle(postLine.substring(7));
				postLine = pbr.readLine();
				post.setContent(postLine.substring(9));
				postLine = pbr.readLine();
				posts.add(post);
				
				postLine = pbr.readLine();
			} while(postLine != null);
			pbr.close();
			
			
			for(int i = 0; i < users.size(); i++) {
				List<Post> usersPosts = new ArrayList<Post>();
				for(int j = 0; j < posts.size(); j++) {
					if(posts.get(j).getAuthor().getId() == users.get(i).getId()) {
						usersPosts.add(posts.get(j));				
					}				
				} 
				users.get(i).setPosts(usersPosts);
			}
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
    	
    	try (BufferedReader fbr = new BufferedReader(new FileReader(followingsFilePath))) {
    		String line = fbr.readLine();
    		while(line != null) {
    			Following f = new Following();
    			
    			String tomb[] = new String[3];
    			tomb = line.split(",");
    			
    			for(int i = 0; i < users.size(); i++) {
    				if(users.get(i).getId() == Integer.parseInt(tomb[0])) {
    					f.setFollower(users.get(i));
    				}
    			}
    			
    			for(int i = 0; i < users.size(); i++) {
    				if(users.get(i).getId() == Integer.parseInt(tomb[1])) {
    					f.setFollowed(users.get(i));
    				}
    			}
    			
    			f.setCreatedAt(LocalDate.parse((tomb[2]).replace('.','-').substring(0,10)));
    			
    			followings.add(f);
    			
    			line = fbr.readLine();
    			
    		}
			fbr.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	@Override
	public List<User> getAllUsers() {
		
		return users;
	}

	@Override
	public List<Following> getAllFollowings() {
		
		return followings;
	}
	
	
}
