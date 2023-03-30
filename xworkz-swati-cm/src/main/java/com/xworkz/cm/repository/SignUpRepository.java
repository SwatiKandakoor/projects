package com.xworkz.cm.repository;

import java.time.LocalDateTime;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.entity.SignUpEntity;

public interface SignUpRepository {
	
	boolean save(SignUpEntity entity);
	
	Integer checkDuplicates (String userId,String email,Double mobile);


}
