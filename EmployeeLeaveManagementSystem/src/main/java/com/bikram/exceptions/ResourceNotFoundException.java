package com.bikram.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String s) {
		super(s);
	}

	public ResourceNotFoundException() {
		super("Employee Not Found !!");
	}
	
}
