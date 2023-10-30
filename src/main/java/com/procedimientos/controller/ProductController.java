package com.procedimientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procedimientos.dto.ProductCreateDto;
import com.procedimientos.dto.ProductDto;
import com.procedimientos.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "")
	public ResponseEntity<ProductDto> insertProduct(@RequestBody ProductCreateDto productCreateDto){
		return new ResponseEntity<>(productService.createProduct(productCreateDto), HttpStatus.CREATED);
	}
}
