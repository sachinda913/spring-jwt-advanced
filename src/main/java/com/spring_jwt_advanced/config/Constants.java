package com.spring_jwt_advanced.config;

public class Constants {
	
	private Constants() {		
	}
	
	public static final int ACCESS_TOKEN_EXPIRY_SECONDS = 60 * 60 *60;
	public static final int REFRESH_TOKEN_EXPIRY_SECONDS = 60 * 60 * 12 ;
	public static final int REDIS_TOKEN_CACHE_EXPIRY_SECONDS = ACCESS_TOKEN_EXPIRY_SECONDS + 30;

}
