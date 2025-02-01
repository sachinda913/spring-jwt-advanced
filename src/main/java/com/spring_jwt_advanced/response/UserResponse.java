package com.spring_jwt_advanced.response;

import lombok.Data;

@Data
public class UserResponse {
	
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Integer roleId;

}
