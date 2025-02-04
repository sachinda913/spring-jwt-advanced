package com.spring_jwt_advanced.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	
	private String accessToken;
	private String token;
	

}
