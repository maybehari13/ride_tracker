/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Mar 12, 2019
 */

package com.pluralsight.util;

/**
 * @author n0172808
 *
 */
public class ServiceError {

	private int code;
	private String message;
	
	public ServiceError(){
	}
	
	public ServiceError(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
