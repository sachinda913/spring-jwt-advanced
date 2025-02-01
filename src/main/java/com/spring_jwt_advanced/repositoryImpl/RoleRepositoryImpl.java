package com.spring_jwt_advanced.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring_jwt_advanced.crudRepository.RoleCrudRepository;
import com.spring_jwt_advanced.entity.Role;
import com.spring_jwt_advanced.repository.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

	@Autowired
	private RoleCrudRepository roleCrudRepository;
	
	@Override
	public Role findById(int roleId) {
		return roleCrudRepository.findById(roleId);
	}

}
