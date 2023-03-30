package com.xworkz.cm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name = "signup_table")
@NamedQuery(name="checkdupl",query = "Select count(entity) from SignUpEntity entity "
		+ " where entity.userId=:userId OR entity.email=:userEmail OR entity.mobile=:userMobile ")
public class SignUpEntity {
	@Id
	@Column(name = "s_signupId")
	private int signUpId;
	@Column(name = "s_userId")
	private String userId;
	@Column(name = "s_email")
	private String email;
	@Column(name = "s_mobile")
	private Double mobile;
	@Column(name = "s_password")
	private String password;
	@Column(name = "s_createBy")
	private String createdBy;
	@Column(name = "s_createDate")
	private LocalDateTime createdDate;
	@Column(name = "s_updateBy")
	private String updateBy;
	@Column(name = "s_updateDate")
	private LocalDateTime updatedDate;
}
