package com.spring_jwt_advanced.serviceImpl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.service.JwtService;
import com.spring_jwt_advanced.service.TokenBlackListService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenBlackListserviceImpl implements TokenBlackListService{

	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public void addToBackList(HttpServletRequest request) {
		
		String token = jwtService.extractTokenFromRequst(request);
		Date expiryDate = jwtService.extractExpirationFromClaims(token);
		
		long expiration = expiryDate.getTime() - System.currentTimeMillis();
		redisTemplate.opsForValue().set(token, "blacklisted",expiration,TimeUnit.MILLISECONDS);		
	}

	@Override
	public Boolean isBackListed(String token) {	
		return redisTemplate.hasKey(token);
	}
	
	public void deleteFromBackList(String token) {
		redisTemplate.delete(token);
	}

}
