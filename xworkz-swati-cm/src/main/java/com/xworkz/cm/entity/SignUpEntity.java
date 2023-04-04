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
@NamedQuery(name="checkdupl",query = "Select count(entity) from SignUpEntity entity where entity.userId=:userId OR entity.email=:userEmail OR entity.mobile=:userMobile " )
@NamedQuery(name="signIn",query = "SELECT entity FROM SignUpEntity entity where entity.userId=:userId AND entity.password=:userPassword")
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
}
