package com.myblog.Exception;

public class ResourceNotFoundException extends RuntimeException{
	
	String msg;
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
