package com.procedimientos.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "documents")
public class Document {
	
	@Id
	@SequenceGenerator(
		name = "sequece_document",
		sequenceName = "sequece_document",
		allocationSize = 1
	)
	@GeneratedValue(generator = "sequece_document", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_document")
	private Long idDocument;
	
	@Column(name = "codigo_document", length = 50, unique = true)
	private String codigoDocument;
	@Column(name = "name_document")
	private String nameDocument;
	@Column(name = "date_create")
	private LocalDate dateCreate;
	@Column(name = "date_update")
	private LocalDate dateUpdate;
	@Column(name = "url_document")
	private String urlDocument;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_product", referencedColumnName = "id_product")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_type", referencedColumnName = "id_type")
	private Type type;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_engineer", referencedColumnName = "id_engineer")
	private Engineer engineer;
}
