package com.xworkz.cm.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data

public class SignUpDto {

	@NotNull
	@Size(min = 2, max = 20, message = "userId should be greater than 3 less than 20")
	private String userId;
	@NotNull
	@Size(min = 3, max = 30, message = "Email should be greater than 3 less than 20")
	private String email;
	@NotNull(message = "Mobile number should be greater than 0 less than 10")
	private Double mobile;
	@NotNull
	@Size(min = 3, max = 30, message = "password should be greater than 3 less than 20")
	private String password;
	private String createdBy = "Swati";
	private LocalDateTime createdDate = LocalDateTime.now();
	private String updateBy;
	private LocalDateTime updatedDate;

}
