package com.xworkz.cm.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.entity.SignUpEntity;

public interface ProjectService {
	
	
	
	Set<ConstraintViolation<SignUpDto>> validateAndSave(SignUpDto dto);
    
	Integer checkDuplicates (String userId, String email, long mobile);
	boolean sendMail(String email);
	
	  default List<SignUpDto> signIn(String userId,String password){
	    	return Collections.emptyList();
	    }
}
