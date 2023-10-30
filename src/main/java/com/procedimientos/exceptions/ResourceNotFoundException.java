package com.procedimientos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String nameResource;
	private String nameField;
	private String valuefield;
	
	public ResourceNotFoundException(String nameResource, String nameField, String valuefield) {
		super("%s no encontrado con: %s -> '%s' ".formatted(nameResource, nameField, valuefield));
		this.nameResource = nameResource;
		this.nameField = nameField;
		this.valuefield = valuefield;
	}
}
