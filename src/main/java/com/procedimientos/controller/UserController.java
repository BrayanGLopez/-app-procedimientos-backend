package com.procedimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.procedimientos.dto.UserCreateDto;
import com.procedimientos.dto.UserDto;
import com.procedimientos.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDto> getUserByUsername(@PathVariable(name = "username") String userName){
		return new ResponseEntity<>(userService.getUserByuserName(userName), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<UserDto> insertUser(@RequestBody UserCreateDto userCreateDto){
		return new ResponseEntity<>(userService.insertUser(userCreateDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserCreateDto userCreateDto){
		return new ResponseEntity<>(userService.UpdateUser(id, userCreateDto), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(userService.DeleteUSer(id), HttpStatus.OK);
	}

}
