package com.procedimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procedimientos.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
