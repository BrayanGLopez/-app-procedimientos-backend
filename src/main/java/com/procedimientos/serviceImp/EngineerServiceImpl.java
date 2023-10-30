package com.procedimientos.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procedimientos.dto.EngineerDto;
import com.procedimientos.model.Engineer;
import com.procedimientos.repository.EngineerRepository;
import com.procedimientos.service.EngineerService;

@Service
public class EngineerServiceImpl implements EngineerService{
	
	@Autowired
	private EngineerRepository engineerRepository;

	@Override
	public EngineerDto convertEngineerToEngineerDto(Engineer engineer) {
		return EngineerDto
				.builder()
				.idEngineer(engineer.getIdEngineer())
				.nameEngineer(engineer.getNameEngineer())
				.build();
	}

	@Override
	public List<EngineerDto> getAllEngineer() {
		return engineerRepository.findAll()
				.stream()
				.map(engineer -> convertEngineerToEngineerDto(engineer))
				.collect(Collectors.toList());
	}

}
