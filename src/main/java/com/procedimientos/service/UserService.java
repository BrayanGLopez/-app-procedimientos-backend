package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.UserCreateDto;
import com.procedimientos.dto.UserDto;

public interface UserService {
	
	public List<UserDto> getAllUsers();
	public UserDto getUserByuserName(String userName);
	public UserDto insertUser(UserCreateDto userCreateDto);
	public UserDto UpdateUser (Long id, UserCreateDto userCreateDto);
	public UserDto DeleteUSer(Long id);
}
