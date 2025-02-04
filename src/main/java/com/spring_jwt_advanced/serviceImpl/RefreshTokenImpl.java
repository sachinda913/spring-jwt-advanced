package com.spring_jwt_advanced.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.RefreshToken;
import com.spring_jwt_advanced.repository.RefreshTokenRepository;
import com.spring_jwt_advanced.repository.UserRepository;
import com.spring_jwt_advanced.service.RefreshTokenService;

@Service
public class RefreshTokenImpl implements RefreshTokenService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private Timestamp timestamp = Timestamp.from(Instant.now().plusSeconds(86400));
	
	public RefreshToken createRefreshToken(String userName) {
		return refreshTokenRepository.save(
				RefreshToken.builder()
				.user(userRepository.findUserByUsername(userName))
				.token(UUID.randomUUID().toString())
				.expiryDate(timestamp)
				.build());				
	}
	
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Timestamp.from(Instant.now())) < 0) {
			refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login!");			
		}
		return token;
	}

}
