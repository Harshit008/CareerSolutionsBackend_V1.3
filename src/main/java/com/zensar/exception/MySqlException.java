package com.zensar.exception;

import java.sql.SQLException;


public class MySqlException extends SQLException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String getSqlException() {
		return "Sorry, Server is Down please try again later!";
	}
}
