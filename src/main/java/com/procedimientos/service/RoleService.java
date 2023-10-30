package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.RoleCreateDto;
import com.procedimientos.dto.RoleDto;

public interface RoleService {
	
	public List<RoleDto> getAllRoles();
	public RoleDto getRoleById(Long id);
	public RoleDto insertRole(RoleCreateDto roleCreateDto);
	public RoleDto updateRole(Long id, RoleCreateDto reCreateDto);
	public RoleDto deleteRole(Long id); 

}
