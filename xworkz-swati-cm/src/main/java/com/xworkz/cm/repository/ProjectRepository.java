package com.xworkz.cm.repository;



import java.util.Collections;
import java.util.List;

import com.xworkz.cm.entity.SignUpEntity;

public interface ProjectRepository {
	
	boolean save(SignUpEntity entity);
	
	Integer checkDuplicates (String userId,String email,long mobile);

    default List<SignUpEntity> signIn(String userId,String password){
    	return Collections.emptyList();
    }
}
