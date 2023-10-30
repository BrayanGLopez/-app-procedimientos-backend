package com.procedimientos.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.UserCreateDto;
import com.procedimientos.dto.UserDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.model.User;
import com.procedimientos.repository.UserRepository;
import com.procedimientos.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleServiceImpl roleService;
	
	private UserDto convertUserToUserDto(User user) {
		return new UserDto(user.getUserId(),
				user.getUsername(),
				roleService.convertRoleToRoleDto(user.getRol()));
	}
	

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map((user) -> convertUserToUserDto(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserByuserName(String userName) {
		
		Optional<User> auxUser = userRepository.findByUserName(userName);
		
		if(!auxUser.isPresent()) {
			return null;
		}
		
		return convertUserToUserDto(auxUser.get());
	}

	@Override
	public UserDto insertUser(UserCreateDto userCreateDto) {
		
		User auxUser = new User(null,
				userCreateDto.getUserName(),
				userCreateDto.getUserPassword(),
				roleService.convertRoleDtoToRole(userCreateDto.getUserRol()));
		
		User newUser = userRepository.save(auxUser);
		
		return convertUserToUserDto(newUser);
	}

	@Override
	public UserDto UpdateUser(Long id, UserCreateDto userCreateDto) {
		
		User findUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id.toString()));
		
		findUser.setUserName(userCreateDto.getUserName());
		findUser.setUserPassword(userCreateDto.getUserPassword());
		findUser.setRol(roleService.convertRoleDtoToRole(userCreateDto.getUserRol()));
		
		return convertUserToUserDto(userRepository.save(findUser));
	}

	@Override
	public UserDto DeleteUSer(Long id) {
		
		User findUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id.toString()));
		
		userRepository.deleteById(id);
		
		return convertUserToUserDto(findUser);
	}
}
