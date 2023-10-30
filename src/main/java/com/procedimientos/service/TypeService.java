package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.TypeDto;
import com.procedimientos.model.Type;

public interface TypeService {
	public TypeDto convertTypeToTypeDto(Type type);
	public List<TypeDto> getAllTypes();
}
