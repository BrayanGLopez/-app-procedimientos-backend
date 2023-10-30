package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.ProductCreateDto;
import com.procedimientos.dto.ProductDto;
import com.procedimientos.dto.ProductResponseDto;
import com.procedimientos.model.Product;

public interface ProductService {
	public ProductDto convertProductToProductDto(Product product);
	public ProductResponseDto convertProductToProductResponseDto(Product product);
	public List<ProductDto> getAllProducts();
	public ProductDto getProductById(int id);
	public ProductDto createProduct(ProductCreateDto productCreateDto);
	public ProductDto updateProduct(int id, String nameString);
	public ProductDto deleteProduct(int id);
}
