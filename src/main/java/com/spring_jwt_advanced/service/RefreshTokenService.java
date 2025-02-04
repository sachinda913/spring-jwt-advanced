package com.spring_jwt_advanced.service;

import java.util.Optional;

import com.spring_jwt_advanced.entity.RefreshToken;

public interface RefreshTokenService {
	
	RefreshToken createRefreshToken(String userName);

	Optional<RefreshToken> findByToken(String token);
	
	RefreshToken verifyExpiration(RefreshToken token);

}
