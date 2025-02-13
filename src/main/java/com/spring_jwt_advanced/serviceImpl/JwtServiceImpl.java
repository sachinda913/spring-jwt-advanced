package com.spring_jwt_advanced.serviceImpl;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring_jwt_advanced.config.Constants;
import com.spring_jwt_advanced.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceImpl implements JwtService{
	
	private String secretKey = "";
	
	public JwtServiceImpl() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			SecretKey key = keyGenerator.generateKey();
			secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String generateAccessToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.claims(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (Constants.ACCESS_TOKEN_EXPIRY_SECONDS)))
				.signWith(getkey())
				.compact();
	}
	
	public String generateRefreshToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.claims(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (Constants.REFRESH_TOKEN_EXPIRY_SECONDS)))
				.signWith(getkey())
				.compact();		
	}
	
	private SecretKey getkey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractUserName(String token) {
		return extractClaim(token , Claims::getSubject);
	}
	
	public Date extractExpirationFromClaims(String token) {
		return extractClaim(token , Claims::getExpiration);
	}

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    	final Claims claims = extractAllClaims(token);    	
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getkey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);		
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpirationFromClaims(token).before(new Date());
	}
	
	public String extractTokenFromRequst(HttpServletRequest request) {
		
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);			
		}
		return null;
		
	}


}
