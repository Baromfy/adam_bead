package com.epam.universities.blog;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import com.epam.universities.blog.domain.*;
import com.epam.universities.blog.persistence.*;
import com.epam.universities.blog.service.*;
import com.epam.universities.blog.view.*;

public class Application {

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.run();
        
		
//		dus.follow(ds.getAllUsers().get(0), ds.getAllUsers().get(3));
		
//		System.out.println(dus.getAllFollowed(ds.getAllUsers().get(1)).get(0).getCredentials().getLoginname());
		
//		System.out.println(dus.getAllFollowers(dus.authenticate(cred)));
//    	if() {
            	
            	
    }

	private void run() throws IOException {  	
    	
    	Path path = Paths.get("D:\\JAVA_Git\\java-basics-blog-application\\data");
		DataStore ds = new FileDataStore(path);
		DefaultUserService dus = new DefaultUserService(ds);
		
		ConsoleUserView cuv = new ConsoleUserView();
		ConsoleGeneralView cgv= new ConsoleGeneralView();
		
		Credentials cred = cuv.readCredentials();
				
		User activeUser = new User();
		
		try {		
			activeUser = dus.authenticate(cred);
			cuv.printWelcomeMessage(activeUser);
		} catch (AuthenticationException ex) {
			cgv.printError(ex.getMessage());
		}
		
		if(activeUser.getCredentials() != null) {
				
			int menuItem = 0;
			
			while(menuItem != 6) {
	    	
		    	System.out.println("1. List my followers (Users who follow me)");
		    	System.out.println("2. List my followings (Users who I follow)");
		    	System.out.println("3. List posts from my follows");
		    	System.out.println("4. Search user");
		    	System.out.println("5. Publish new post");
		    	System.out.println("6. Quit application");
		    	System.out.println("Select Menu Item(1-6) : ");
		
				menuItem = cgv.selectMenuItem();
				
		    	switch(menuItem) {
		    	case 1:
		    		cuv.printAllFollowers(dus.getAllFollowers(activeUser));	    		
		    		break;
		    	case 2:
		    		cuv.printAllFollowed(dus.getAllFollowed(activeUser));	    		
		    		break;
		    	case 3:
		    		break;
		    	case 4:
					
					String fullName = cuv.readUsernameToBeSearched();
					
					System.out.println("===");
					User u;
					u = dus.searchUser(fullName).get();	
					int followers = dus.getAllFollowers(u).size();
					int followed = dus.getAllFollowed(u).size();
					cuv.printUserProfile(u, followers, followed);					
								
					if(cuv.readIfUserWantsToFollow()) {
						dus.follow(activeUser, u);
						
						System.out.println(u.getProfile().getName() + " has been followed.");
						break;
					} else {
						break;
					}
							
		    	case 5:
		    		break;
		    	case 6: 
		    		cgv.quit(activeUser);
		    		break;
		    	default:
		    	}
			}
		}
    }
}
