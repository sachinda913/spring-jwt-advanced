package com.spring_jwt_advanced.service;


import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.response.AuthResponse;
import com.spring_jwt_advanced.response.JwtResponse;
import com.spring_jwt_advanced.response.RefreshTokenResponse;
import com.spring_jwt_advanced.response.UserResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	User createUser(UserResponse userResponse);

	JwtResponse loginAndGenerateToken(AuthResponse authResponse);

	JwtResponse generateRefreshToken(RefreshTokenResponse refreshTokenResponse);

	String logout(HttpServletRequest request);

	Boolean checkBacklist(String token);

}
