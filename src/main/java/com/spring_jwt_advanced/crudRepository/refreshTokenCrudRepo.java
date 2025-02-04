package com.spring_jwt_advanced.crudRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_jwt_advanced.entity.RefreshToken;

import jakarta.transaction.Transactional;

@Transactional
public interface refreshTokenCrudRepo extends JpaRepository<RefreshToken, Integer>{

	Optional<RefreshToken> findByToken(String token);

}
