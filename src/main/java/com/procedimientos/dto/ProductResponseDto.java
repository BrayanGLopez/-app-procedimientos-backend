package com.procedimientos.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductResponseDto {
	private int idProduct;
	private String nameProduct;
	private List<DocumentoResponseProductDto> documents; 
}
