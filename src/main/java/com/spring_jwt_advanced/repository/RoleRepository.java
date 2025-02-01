package com.spring_jwt_advanced.repository;

import org.springframework.stereotype.Service;

import com.spring_jwt_advanced.entity.Role;

@Service
public interface RoleRepository {

	Role findById(int roleId);

}
