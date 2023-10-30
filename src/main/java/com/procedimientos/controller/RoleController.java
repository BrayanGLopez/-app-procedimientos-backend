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

import com.procedimientos.dto.RoleCreateDto;
import com.procedimientos.dto.RoleDto;
import com.procedimientos.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "")
	public List<RoleDto> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<RoleDto> insertRole(@RequestBody RoleCreateDto roleCreateDto) {
		return new ResponseEntity<>(roleService.insertRole(roleCreateDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RoleDto> updateRole(@PathVariable(name = "id") Long id, @RequestBody RoleCreateDto roleCreateDto){
		return new ResponseEntity<>(roleService.updateRole(id, roleCreateDto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RoleDto> deleteRole(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(roleService.deleteRole(id), HttpStatus.OK);
	}

}
