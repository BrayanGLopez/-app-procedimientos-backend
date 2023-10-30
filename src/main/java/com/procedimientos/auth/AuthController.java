package com.procedimientos.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procedimientos.dto.UserCreateDto;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
	}
	
	@PostMapping(value = "registrar")
	public ResponseEntity<AuthResponse> registar(@RequestBody UserCreateDto userCreateDto) {
		return new ResponseEntity<>(authService.register(userCreateDto), HttpStatus.OK);
	}
	
}
