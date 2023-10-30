package com.procedimientos.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.ClientDto;
import com.procedimientos.dto.ClientResponseDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.model.Client;
import com.procedimientos.repository.ClientRepository;
import com.procedimientos.service.ClientService;
import com.procedimientos.service.ProductService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public ClientDto convertClientToClientDto(Client client){
		return  ClientDto.builder()
					.idClient(client.getIdClient())
					.nameClient(client.getNameClient())
					.products(client.getProducts()
									.stream()
									.map(product -> productService.convertProductToProductResponseDto(product))
									.collect(Collectors.toList()))
					.build();
	}
	
	@Override
	public ClientResponseDto convertClientToClientResponseDto(Client client) {
		return ClientResponseDto.builder()
				.idClient(client.getIdClient())
				.nameClient(client.getNameClient())
				.build();
	}
		
	@Override
	public List<ClientDto> getAllClients() {
		return clientRepository.findAll()
				.stream()
				.map((client) -> convertClientToClientDto(client))
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto getClientById(int id) {
		
		Client clientAux = clientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", Integer.toString(id)));
		
		return convertClientToClientDto(clientAux);
	}

	@Override
	public ClientDto createClient(ClientResponseDto clientResponseDto) {
		
		Client clientAux = Client.builder()
				.idClient(clientResponseDto.getIdClient())
				.nameClient(clientResponseDto.getNameClient())
				.build();
		return convertClientToClientDto(clientRepository.save(clientAux));
	}

	@Override
	public ClientDto updateClient(int id, ClientResponseDto clientResponseDto) {
		
		Client clientAux = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", Integer.toString(id)));
		
		clientAux.setNameClient(clientResponseDto.getNameClient());
		
		return convertClientToClientDto(clientRepository.save(clientAux));
	}

	@Override
	public ClientDto deleteClient(int id) {
		
		Client clientAux = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", Integer.toString(id)));
		
		clientRepository.deleteById(id);
		
		return convertClientToClientDto(clientAux);
	}

}
