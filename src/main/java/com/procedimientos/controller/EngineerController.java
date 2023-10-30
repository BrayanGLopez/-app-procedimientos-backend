package com.procedimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procedimientos.dto.EngineerDto;
import com.procedimientos.service.EngineerService;

@RestController
@RequestMapping(value = "/engineer")
public class EngineerController {
	
	@Autowired
	private EngineerService engineerService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<EngineerDto>> getAllEngineers(){
		return new ResponseEntity<>(engineerService.getAllEngineer(), HttpStatus.OK);
	}

}
