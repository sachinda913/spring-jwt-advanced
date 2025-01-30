package com.spring_jwt_advanced.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring_jwt_advanced.crudRepository.UserCrudRepository;
import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	@Override
	public User findById(int id) {
		return userCrudRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userCrudRepository.save(user);
	}

	@Override
	public User findUserByUsername(String userName) {
		return userCrudRepository.findUserByUsername(userName);
	}

}
