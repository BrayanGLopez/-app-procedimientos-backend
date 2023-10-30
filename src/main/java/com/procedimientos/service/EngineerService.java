package com.procedimientos.service;

import java.util.List;

import com.procedimientos.dto.EngineerDto;
import com.procedimientos.model.Engineer;

public interface EngineerService {
	
	public EngineerDto convertEngineerToEngineerDto(Engineer engineer);
	public List<EngineerDto> getAllEngineer();

}
