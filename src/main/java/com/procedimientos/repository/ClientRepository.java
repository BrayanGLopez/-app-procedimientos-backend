package com.procedimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procedimientos.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
