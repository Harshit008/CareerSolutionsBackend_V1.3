package com.zensar.exception;

public class GlobalExceptionHandler extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public GlobalExceptionHandler(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public GlobalExceptionHandler(String exMessage) {
		super(exMessage);
	}
}
