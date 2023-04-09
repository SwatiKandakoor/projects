package com.xworkz.cm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "signup_table")
@NamedQuery(name = "checkdupl", query = "Select count(entity) from SignUpEntity entity where entity.userId=:userId OR entity.email=:userEmail OR entity.mobile=:userMobile ")
@NamedQuery(name = "signIn", query = "SELECT entity FROM SignUpEntity entity where entity.userId=:userId ")
@NamedQuery(name = "lockCount", query = "Update SignUpEntity entity set entity.loginCount=:lock where entity.userId=:userId")
@NamedQuery(name = "findByEmail", query = "Select entity from SignUpEntity entity where entity.email=:email")
@NamedQuery(name = "updatePassword", query = "update SignUpEntity entity set entity.password =: password, entity.resetPwd=:resetPwd,"
		+ "entity.updateBy=:updatedBy,entity.updatedDate=:updatedDate where entity.email=:email")
@NamedQuery(name = "updateConfirmPassword", query = "update SignUpEntity entity set entity.password =: password, entity.resetPwd=:resetPwd,"
		+ "entity.updateBy=:updatedBy,entity.updatedDate=:updatedDate where entity.userId=:userId")
//@NamedQuery(name="checkdupl",query="select userId,email,mobile ,count(entity)from SignUpEntity entity where entity.userId=:userId Or entity.email=:userEmail or entity.mobile=:userMobile group by 1,2,3")
public class SignUpEntity {
	@Id
	@Column(name = "signupId")
	private int signUpId;
	@Column(name = "userId")
	private String userId;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private long mobile;
	@Column(name = "password")
	private String password;
	@Column(name = "createBy")
	private String createdBy;
	@Column(name = "createDate")
	private LocalDateTime createdDate;
	@Column(name = "updateBy")
	private String updateBy;
	@Column(name = "updateDate")
	private LocalDateTime updatedDate;
	@Column(name = "login_count")
	private int loginCount;
	@Column(name = "reset_pwd")
	private boolean resetPwd;
}
