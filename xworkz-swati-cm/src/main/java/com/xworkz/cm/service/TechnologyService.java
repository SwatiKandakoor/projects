package com.xworkz.cm.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.cm.dto.AddTechnologyDto;

public interface TechnologyService {

	
	Set<ConstraintViolation<AddTechnologyDto>> validateAndAdd(AddTechnologyDto dto);

}
