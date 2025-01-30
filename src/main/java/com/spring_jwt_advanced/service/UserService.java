package com.spring_jwt_advanced.service;

import com.spring_jwt_advanced.entity.User;

public interface UserService {

	User createUser(User user);

	String loginAndGenerateToken(User user);

}
