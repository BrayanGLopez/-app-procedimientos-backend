package com.procedimientos.auth;

import com.procedimientos.dto.UserCreateDto;

public interface AuthService {
	public AuthResponse login(LoginRequest loginRequest);
	public AuthResponse register(UserCreateDto userCreateDto);

}
