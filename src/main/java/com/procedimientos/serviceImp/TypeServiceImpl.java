package com.procedimientos.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.TypeDto;
import com.procedimientos.model.Type;
import com.procedimientos.repository.TypeRepository;
import com.procedimientos.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Override
	public TypeDto convertTypeToTypeDto(Type type) {
		return TypeDto
				.builder()
				.idType(type.getIdType())
				.nameType(type.getNameType())
				.build();
	}

	@Override
	public List<TypeDto> getAllTypes() {
		return typeRepository.findAll()
				.stream()
				.map( (type) -> convertTypeToTypeDto(type))
				.collect(Collectors.toList());
	}

	

}
