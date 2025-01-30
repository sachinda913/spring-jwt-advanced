package com.spring_jwt_advanced.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_jwt_advanced.entity.Student;
import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	List<Student> students = new ArrayList<>(List.of(
			new Student(1,"Sachinda",24)));

		
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
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/login-user")
	public String loginAndGenerateToken(@RequestBody User user) {
		return userService.loginAndGenerateToken(user);
	}
	
	

}
