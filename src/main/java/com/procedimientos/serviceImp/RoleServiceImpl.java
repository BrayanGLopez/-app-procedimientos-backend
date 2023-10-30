package com.procedimientos.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.RoleCreateDto;
import com.procedimientos.dto.RoleDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.model.Role;
import com.procedimientos.repository.RoleRepository;
import com.procedimientos.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	public RoleDto convertRoleToRoleDto(Role role) {
		return new RoleDto(role.getRoleId(), role.getRoleName());
	}
	
	public Role convertRoleDtoToRole(RoleDto roleDto) {
		return new Role(roleDto.getRoleId(), roleDto.getRoleName());
	}
	
	@Override
	public List<RoleDto> getAllRoles() {
		return roleRepository.findAll()
				.stream().map( role  -> convertRoleToRoleDto(role))
				.collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleById(Long id) {
		
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
		
		RoleDto roleDto = convertRoleToRoleDto(role);
		
		return roleDto;
	}

	@Override
	public RoleDto insertRole(RoleCreateDto roleCreateDto) {
		
		Role auxRole = new Role(null, roleCreateDto.getRoleName());
		Role newRole = roleRepository.save(auxRole);
		
		return convertRoleToRoleDto(newRole);
	}

	@Override
	public RoleDto updateRole(Long id, RoleCreateDto roleCreateDto) {
		
		Role findRole = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
	
		
		findRole.setRoleName(roleCreateDto.getRoleName());
		
		return convertRoleToRoleDto(findRole);
	}

	@Override
	public RoleDto deleteRole(Long id) {
		
		Role auxRole = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
		
		roleRepository.deleteById(id);
		
		return convertRoleToRoleDto(auxRole);
	}

}
