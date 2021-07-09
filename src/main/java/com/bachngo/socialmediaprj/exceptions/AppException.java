package com.bachngo.socialmediaprj.exceptions;

/**
 * custom exception
 * @author Bach
 *
 */
public class AppException extends RuntimeException{
	
	public AppException(String message, Exception exception) {
		super(message, exception);
	}
	
	public AppException(String message) {
		super(message);
	}
}
