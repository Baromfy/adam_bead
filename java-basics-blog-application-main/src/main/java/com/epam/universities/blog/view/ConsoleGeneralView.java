package com.epam.universities.blog.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.universities.blog.Application;
import com.epam.universities.blog.domain.*;
import com.epam.universities.blog.service.*;

public class ConsoleGeneralView implements GeneralView{

	@Override
	public int selectMenuItem() throws NumberFormatException, IOException {		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int menuItem = Integer.parseInt(reader.readLine());
		return menuItem;
	}

	@Override
	public void quit(User user) {
		System.out.println("Quitting Blogger Application...\nGoodbye " + user.getProfile().getName() + "!");
		System.exit(1);		
	}

	@Override
	public void printError(String message) {
		System.out.println("===\n" + message + "\n===");
	}

}
