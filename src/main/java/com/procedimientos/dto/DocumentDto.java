package com.procedimientos.dto;

import java.time.LocalDate;

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
public class DocumentDto {
	private Long idDocument;
	private String nameDocument;
	private String codigoDocument;
	private LocalDate dateCreate;
	private LocalDate dateUpdate;
	private String urlDocument;
	private ProductDto product;
	private TypeDto type;
	private EngineerDto engineer;
}
