package com.spring_jwt_advanced.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String extractUserName(String token);

	boolean validateToken(String token, UserDetails userDetails);

}
