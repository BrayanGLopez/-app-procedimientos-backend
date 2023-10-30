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
public class DocumentResponseDto {
	private Long idDocument;
	private String nameDocument;
	private String codigoDocument;
	private LocalDate dateCreate;
	private LocalDate dateUpdate;
	private String urlDocument;
	private String nameClient;
	private String nameProduct;
	private String type;
	private String nameEngineer;
}
