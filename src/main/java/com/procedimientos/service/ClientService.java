package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.ClientDto;
import com.procedimientos.dto.ClientResponseDto;
import com.procedimientos.model.Client;

public interface ClientService {
	public ClientDto convertClientToClientDto(Client client);
	public ClientResponseDto convertClientToClientResponseDto(Client client);
	public List<ClientDto> getAllClients();
	public ClientDto getClientById(int id);
	public ClientDto createClient(ClientResponseDto clientResponseDto);
	public ClientDto updateClient(int id, ClientResponseDto clientResponseDto);
	public ClientDto deleteClient(int id);
}
