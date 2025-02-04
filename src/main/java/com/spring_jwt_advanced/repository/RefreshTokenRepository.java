package com.spring_jwt_advanced.repository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.RefreshToken;

@Service
public interface RefreshTokenRepository {

	RefreshToken save(RefreshToken refreshToken);

	Optional<RefreshToken> findByToken(String token);

	void delete(RefreshToken token);

}
