package com.procedimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procedimientos.dto.TypeDto;
import com.procedimientos.service.TypeService;

@RestController
@RequestMapping(value = "/type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<TypeDto>> getAllTypes(){
		return new ResponseEntity<>(typeService.getAllTypes(), HttpStatus.OK);
	}
}
