package com.spring_jwt_advanced.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.User;
import com.spring_jwt_advanced.entity.UserPrincipal;
import com.spring_jwt_advanced.repository.UserRepository;

@Service
public class UserServiceMyImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		User user = userRepository.findUserByUsername(userName);
		
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}		
		return new UserPrincipal(user); 
	}

}
