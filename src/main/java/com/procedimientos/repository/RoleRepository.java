package com.procedimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procedimientos.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
