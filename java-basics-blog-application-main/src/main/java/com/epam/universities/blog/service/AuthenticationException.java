package com.epam.universities.blog.service;

public class AuthenticationException extends RuntimeException{
	
	public AuthenticationException(String message) {
		super (message);
	}
}
