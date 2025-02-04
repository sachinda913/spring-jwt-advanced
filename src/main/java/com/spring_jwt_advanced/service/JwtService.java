package com.spring_jwt_advanced.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {

	String extractUserName(String token);

	boolean validateToken(String token, UserDetails userDetails);
	
	String extractTokenFromRequst(HttpServletRequest request);

	Date extractExpirationFromClaims(String token);
	
}
