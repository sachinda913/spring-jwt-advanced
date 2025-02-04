package com.spring_jwt_advanced.service;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenBlackListService {
	
	void addToBackList(HttpServletRequest request);
	
	Boolean isBackListed(String token);

}
