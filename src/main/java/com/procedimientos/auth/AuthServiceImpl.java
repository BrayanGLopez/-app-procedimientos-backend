package com.procedimientos.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.UserCreateDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.jwt.JwtService;
import com.procedimientos.model.User;
import com.procedimientos.repository.UserRepository;
import com.procedimientos.serviceImp.RoleServiceImpl;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		UserDetails user = userRepository.findByUserName(loginRequest.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "username", loginRequest.getUserName()));
		String token = jwtService.getToken(user);
		
		return AuthResponse.builder()
				.token(token)
				.build();
	}

	@Override
	public AuthResponse register(UserCreateDto userCreateDto) {
		
		User user = User.builder()
				.userName(userCreateDto.getUserName())
				.userPassword(passwordEncoder.encode(userCreateDto.getUserPassword()))
				.rol(roleServiceImpl.convertRoleDtoToRole(userCreateDto.getUserRol()))
				.build();
		
		userRepository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();

	}

}
