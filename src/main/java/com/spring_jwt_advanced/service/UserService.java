package com.spring_jwt_advanced.service;

import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.response.UserResponse;

public interface UserService {

	User createUser(UserResponse userResponse);

	String loginAndGenerateToken(User user);

}
