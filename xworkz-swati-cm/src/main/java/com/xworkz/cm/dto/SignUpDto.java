package com.xworkz.cm.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data

public class SignUpDto {
   
    @NotNull
	@Size(min = 3, max = 20, message = "userId should be greater than 3 less than 20")
    private String userId;
    @NotNull
	@Size(min = 3, max = 20, message = "Email should be greater than 3 less than 20")
    private String email;
    @NotNull(message="mobile number should be less than 10")
    private double mobile;
    @NotNull
	@Size(min = 3, max = 20, message = "password should be greater than 3 less than 20")
    private String password;
    @NotNull
	@Size(min = 3, max = 20, message = "createdBy should be greater than 3 less than 20")
	private String createdBy;
	private LocalDateTime createdDate;
	@NotNull
	@Size(min = 3, max = 20, message = "updateBy should be greater than 3 less than 20")
	private String updateBy;
	private LocalDateTime updatedDate;


}
