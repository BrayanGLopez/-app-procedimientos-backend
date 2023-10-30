package com.procedimientos.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.ClientResponseDto;
import com.procedimientos.dto.ProductCreateDto;
import com.procedimientos.dto.ProductDto;
import com.procedimientos.dto.ProductResponseDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.model.Client;
import com.procedimientos.model.Product;
import com.procedimientos.repository.ClientRepository;
import com.procedimientos.repository.ProductRepository;
import com.procedimientos.service.DocumentService;
import com.procedimientos.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private DocumentService documentService;

	@Override
	public ProductDto convertProductToProductDto(Product product) {
		return ProductDto.builder()
				.idProduct(product.getIdProduct())
				.nameProduct(product.getNameProduct())
				.client(ClientResponseDto.builder()
						.idClient(product.getClient().getIdClient())
						.nameClient(product.getClient().getNameClient())
						.build())
				.build();
	}
	
	@Override
	public ProductResponseDto convertProductToProductResponseDto(Product product) {
		return ProductResponseDto.builder()
				.idProduct(product.getIdProduct())
				.nameProduct(product.getNameProduct())
				.documents(product.getDocuments()
						.stream()
						.map(document -> documentService.convertDocumentToDocumentResponseProductDto(document))
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	public List<ProductDto> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(product -> convertProductToProductDto(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(int id) {
		
		Product productAux = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto", "id", Integer.toString(id)));
		
		return convertProductToProductDto(productAux);
	}

	@Override
	public ProductDto createProduct(ProductCreateDto productCreateDto) {
				
		Client clientAux;
		Optional<Client> client = clientRepository.findById(productCreateDto.getClient().getIdClient());
				
		if(!client.isPresent()){
			clientAux = clientRepository.save(Client.builder()
													.idClient(productCreateDto.getClient().getIdClient())
													.nameClient(productCreateDto.getClient().getNameClient())
													.build());
		}else {
			clientAux = client.get();
		}
		
		Product productAux = Product.builder()
									.idProduct(productCreateDto.getIdProduct())
									.nameProduct(productCreateDto.getNameProduct())
									.client(clientAux)
									.build();
		
		return convertProductToProductDto(productRepository.save(productAux));
	}

	@Override
	public ProductDto updateProduct(int id, String nameString) {
		
		Product productAux = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto", "id", Integer.toString(id)));
		productAux.setNameProduct(nameString);
		
		return convertProductToProductDto(productAux);
	}

	@Override
	public ProductDto deleteProduct(int id) {
		
		Product productAux = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto", "id", Integer.toString(id)));
		
		productRepository.deleteById(id);
		
		return convertProductToProductDto(productAux);
	}	
}
