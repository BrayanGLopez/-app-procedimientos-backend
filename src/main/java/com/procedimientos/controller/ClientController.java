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

import com.procedimientos.dto.ClientDto;
import com.procedimientos.dto.ClientResponseDto;
import com.procedimientos.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping(value = "")
	public ResponseEntity<List<ClientDto>> getAllClients(){
		return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<ClientDto> createClient(@RequestBody ClientResponseDto clientResponseDto){
		return new ResponseEntity<>(clientService.createClient(clientResponseDto), HttpStatus.CREATED); 
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDto> updateClient(@PathVariable(name = "id") int id, @RequestBody ClientResponseDto clientResponseDto){
		return new ResponseEntity<>(clientService.updateClient(id, clientResponseDto), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDto> deleteClient(@PathVariable(name = "id") int id){
		return new ResponseEntity<>(clientService.deleteClient(id), HttpStatus.OK);
	}
}
