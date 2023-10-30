package com.procedimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procedimientos.model.Engineer;

public interface EngineerRepository extends JpaRepository<Engineer, Integer>{

}
