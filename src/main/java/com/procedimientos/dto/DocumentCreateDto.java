package com.procedimientos.dto;


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
public class DocumentCreateDto {
	private String codigoDocument;
	private String urlDocument;
	private ProductDto product;
	private TypeDto type;
	private EngineerDto engineer;
}
