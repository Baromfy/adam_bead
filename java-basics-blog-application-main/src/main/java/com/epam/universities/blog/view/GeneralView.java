package com.epam.universities.blog.view;

import java.io.IOException;

import com.epam.universities.blog.domain.User;

public interface GeneralView {

	public int selectMenuItem() throws NumberFormatException, IOException;
	
	public void quit(User user);
	
	public void printError(String message);
}
