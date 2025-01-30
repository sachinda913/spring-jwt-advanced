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

import com.spring_jwt_advanced.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
				.and()
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

	private boolean isTokenExpired(String token) {
		return extractExpirationFromClaims(token).before(new Date());
	}

	private Date extractExpirationFromClaims(String token) {
		return extractClaim(token , Claims::getExpiration);
	}

}
