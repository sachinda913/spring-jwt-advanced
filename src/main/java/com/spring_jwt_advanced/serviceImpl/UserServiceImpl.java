package com.spring_jwt_advanced.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.repository.UserRepository;
import com.spring_jwt_advanced.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));		
		return userRepository.save(user);
	}

	@Override
	public String loginAndGenerateToken(User user) {		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtServiceImpl.generateToken(user.getUsername());
		}else {
			return "Fail";
		}		
	}

}
