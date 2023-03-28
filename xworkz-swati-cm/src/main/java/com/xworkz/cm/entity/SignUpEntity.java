package com.xworkz.cm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name = "signup_table")

public class SignUpEntity {
	@Id
	@Column(name = "s_singupId")
	private int signUpId;
	@Column(name = "s_userId")
	private String userId;
	@Column(name = "s_email")
	private String email;
	@Column(name = "s_mobile")
	private double mobile;
	@Column(name = "s_password")
	private String password;
	@Column(name = "s_createdBy")
	private String createdBy="Swati";
	@Column(name = "s_createdDate")
	private LocalDateTime createdDate=LocalDateTime.now();
	@Column(name = "s_updateBy")
	private String updateBy;
	@Column(name = "s_updatedDate")
	private LocalDateTime updatedDate;
}
