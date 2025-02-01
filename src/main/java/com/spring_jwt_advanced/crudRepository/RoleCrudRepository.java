package com.spring_jwt_advanced.crudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_jwt_advanced.entity.Role;

import jakarta.transaction.Transactional;

@Transactional
public interface RoleCrudRepository extends JpaRepository<Role, Integer>{

	Role findById(int roleId);

}
