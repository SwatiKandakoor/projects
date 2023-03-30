package com.xworkz.cm.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.entity.SignUpEntity;
import com.xworkz.cm.repository.SignUpRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpRepository repository;

	public SignUpServiceImpl() {
		log.info("create " + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<SignUpDto>> validateAndSave(SignUpDto dto) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<SignUpDto>> constraintViolations = validator.validate(dto);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			System.err.println("constraintViolations exists,return constraints");
			return constraintViolations;
		} else {
			log.info("constraintViolations does not exist,data is good");
			SignUpEntity entity = new SignUpEntity();
			BeanUtils.copyProperties(dto, entity);
			boolean saved = this.repository.save(entity);
			
			System.out.println(saved);
			System.out.println("Dto" + dto);
			log.info("ENTITY" + entity);
			return Collections.emptySet();
		}
	}

	@Override
	public Integer checkDuplicates(String userId, String email, Double mobile) {
       Integer count=this.repository.checkDuplicates(userId, email, mobile);
           SignUpEntity entity=new SignUpEntity();
    	   SignUpDto dto=new SignUpDto();
           dto.setUserId(entity.getUserId());
           dto.setEmail(entity.getEmail());
           dto.setMobile(entity.getMobile());
      
		return count;
	}
}