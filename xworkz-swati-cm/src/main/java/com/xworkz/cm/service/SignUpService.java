package com.xworkz.cm.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.cm.dto.SignUpDto;

public interface SignUpService {
	
	
	
	Set<ConstraintViolation<SignUpDto>> validateAndSave(SignUpDto dto);
    
	Integer checkDuplicates (String userId, String email, Double mobile);
}
