package com.spring_jwt_advanced.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring_jwt_advanced.crudRepository.refreshTokenCrudRepo;
import com.spring_jwt_advanced.entity.RefreshToken;
import com.spring_jwt_advanced.repository.RefreshTokenRepository;

@Repository
public class RefreshTokenRepoImpl implements RefreshTokenRepository{
	
	@Autowired
	private refreshTokenCrudRepo refreshTokenCrudRepo;

	@Override
	public RefreshToken save(RefreshToken refreshToken) {
		return refreshTokenCrudRepo.save(refreshToken);
	}

	@Override
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenCrudRepo.findByToken(token);
	}

	@Override
	public void delete(RefreshToken token) {
		return;
	}

}
