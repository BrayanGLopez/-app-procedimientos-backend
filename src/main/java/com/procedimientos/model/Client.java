package com.procedimientos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "clients")
public class Client {
	
	@Id
	@Column(name = "id_client")
	private int idClient;
	@Column(name = "name_client", unique = true, nullable = false)
	private String nameClient;
	
	@OneToMany(mappedBy = "client")
	private List<Product> products;
}
