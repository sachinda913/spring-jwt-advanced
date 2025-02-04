package com.spring_jwt_advanced.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.RefreshToken;
import com.spring_jwt_advanced.entity.Role;
import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.repository.RoleRepository;
import com.spring_jwt_advanced.repository.UserRepository;
import com.spring_jwt_advanced.response.AuthResponse;
import com.spring_jwt_advanced.response.JwtResponse;
import com.spring_jwt_advanced.response.RefreshTokenResponse;
import com.spring_jwt_advanced.response.UserResponse;
import com.spring_jwt_advanced.service.RefreshTokenService;
import com.spring_jwt_advanced.service.TokenBlackListService;
import com.spring_jwt_advanced.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private TokenBlackListService tokenBlackListService;
		
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	

	@Override
	public User createUser(UserResponse userResponse) {
		
		Role role = roleRepository.findById(userResponse.getRoleId());
				
		User user = new User();
		user.setFirstname(userResponse.getFirstname());
		user.setLastname(userResponse.getLastname());
		user.setUsername(userResponse.getUsername());
		user.setEmail(userResponse.getEmail());					
		user.setPassword(passwordEncoder.encode(userResponse.getPassword()));
		user.setRole(role);
		return userRepository.save(user);
	}

	@Override
	public JwtResponse loginAndGenerateToken(AuthResponse authResponse) {		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authResponse.getUserName(), authResponse.getPassword()));
		
		if(authentication.isAuthenticated()) {		
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(authResponse.getUserName());
			return JwtResponse.builder()
					.accessToken(jwtServiceImpl.generateToken(authResponse.getUserName()))
					.token(refreshToken.getToken())
					.build();			
		}else {
			logger.error("User Name Not Found for UserName is {}" ,authResponse.getUserName());
			throw new UsernameNotFoundException("Invalid User Found!!");
		}		
	}

	@Override
	public JwtResponse generateRefreshToken(RefreshTokenResponse refreshTokenResponse) {
		return refreshTokenService.findByToken(refreshTokenResponse.getToken())
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user ->{
					String accessToken = jwtServiceImpl.generateToken(user.getUsername());
					return JwtResponse.builder()
							.accessToken(accessToken)
							.token(refreshTokenResponse.getToken()).build();
				}).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));

	}

	@Override
	public String logout(HttpServletRequest request) {
		tokenBlackListService.addToBackList(request);
		return "Log Out Successfull";
	}

	@Override
	public Boolean checkBacklist(String token) {
		return tokenBlackListService.isBackListed(token);
	}

}
