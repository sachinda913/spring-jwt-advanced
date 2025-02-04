package com.spring_jwt_advanced.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring_jwt_advanced.service.JwtService;
import com.spring_jwt_advanced.service.TokenBlackListService;
import com.spring_jwt_advanced.serviceImpl.UserServiceMyImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	TokenBlackListService tokenBlackListService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		String token = null;
		String userName = null;
				
		if(authorization != null && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			userName = jwtService.extractUserName(token);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = applicationContext.getBean(UserServiceMyImpl.class).loadUserByUsername(userName);
			
			if(jwtService.validateToken(token,userDetails) && !tokenBlackListService.isBackListed(token)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);	
			}			
		}
		filterChain.doFilter(request, response);				
	}

}
