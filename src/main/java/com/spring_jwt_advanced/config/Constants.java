package com.spring_jwt_advanced.config;

public class Constants {
	
	private Constants() {		
	}
	
	public static final int ACCESS_TOKEN_EXPIRY_SECONDS = 2 * 60 + 30;
	public static final int REFRESH_TOKEN_EXPIRY_SECONDS = 1 * 60 * 5 ;
	public static final int REDIS_TOKEN_CACHE_EXPIRY_SECONDS = ACCESS_TOKEN_EXPIRY_SECONDS + 30;

}
