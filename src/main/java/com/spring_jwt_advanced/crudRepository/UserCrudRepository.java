package com.spring_jwt_advanced.crudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_jwt_advanced.entity.User;

import jakarta.transaction.Transactional;

@Transactional
public interface UserCrudRepository extends JpaRepository<User, Integer>{
	
	User findById(int id);

	User findUserByUsername(String userName);

}
