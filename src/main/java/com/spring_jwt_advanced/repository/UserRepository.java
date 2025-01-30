package com.spring_jwt_advanced.repository;

import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.User;

@Service
public interface UserRepository {
	
	User findById(int id);
	
	User save(User user);

	User findUserByUsername(String userName);

}
