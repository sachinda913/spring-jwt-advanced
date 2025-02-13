package com.spring_jwt_advanced.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_jwt_advanced.entity.Student;
import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.response.AuthResponse;
import com.spring_jwt_advanced.response.JwtResponse;
import com.spring_jwt_advanced.response.RefreshTokenResponse;
import com.spring_jwt_advanced.response.UserResponse;
import com.spring_jwt_advanced.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/userController")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	List<Student> students = new ArrayList<>(
			List.of(new Student(1,"Sachinda",24)));

	
	@GetMapping("/get-test")
	public List<Student> test(HttpServletRequest httpServlet) {
		return students;		
	}
	
	@PostMapping("/post-test")
	public Student createStudent(@RequestBody Student student) {
		students.add(student);
		return student;		
	}
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody UserResponse userResponse) {
		return userService.createUser(userResponse);
	}
	
	@PostMapping("/login-user")
	public JwtResponse loginAndGenerateToken(@RequestBody AuthResponse authResponse) {
		return userService.loginAndGenerateToken(authResponse);
	}
	
	@PostMapping("/refresh-token")
	public JwtResponse generateRefreshToken(@RequestBody RefreshTokenResponse refreshToken) {
		return userService.generateRefreshToken(refreshToken);
	}
	
	@PostMapping("/log-out")
	public String logout(HttpServletRequest request) {
		return userService.logout(request);
	}
	
	@GetMapping("/check-backlist")
	public Boolean checkBacklist(@RequestParam String token) {
		logger.info("Checking Token is : {}" ,token);
		return userService.checkBacklist(token);
	}
	
	
	

}
