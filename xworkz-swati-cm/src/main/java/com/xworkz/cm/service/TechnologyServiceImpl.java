package com.xworkz.cm.service;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.xworkz.cm.dto.AddTechnologyDto;
import com.xworkz.cm.entity.TechnolgyListEntity;
import com.xworkz.cm.repository.TechnologyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

	private TechnologyRepository technologyRepository;

	@Override
	public Set<ConstraintViolation<AddTechnologyDto>> validateAndAdd(AddTechnologyDto dto) {
		log.info("validateAndAdd in TechnologyServiceImpl");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<AddTechnologyDto>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			log.info("Violations in dto" + dto);
			return violations;
		}
		TechnolgyListEntity entity = new TechnolgyListEntity();
		BeanUtils.copyProperties(dto, entity);
		boolean add = this.technologyRepository.add(entity);
		if (add) {
			log.info("Technology added successfully");
		}
		return Collections.emptySet();
	}
}
