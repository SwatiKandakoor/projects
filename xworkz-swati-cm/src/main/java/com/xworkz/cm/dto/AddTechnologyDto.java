package com.xworkz.cm.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTechnologyDto {
	private int id;

	@NotNull(message= "Name can't be null")
	private String name;
	
	@NotNull(message = "Language can't be null")
	private String language;
	
	@NotNull(message = "Version can't be null")
	private String version;
	
	@NotNull(message = "Owner can't be null")
	private String owner;
	
	@NotNull(message = "Support From can't be null")
	private String supportFrom;
	
	@NotNull(message = "Support To can't be null")
	private String supportTo;
	
	@NotNull(message = "License can't be null")
	private String license;
	
	
	@NotNull(message = "OS Type can't be null")
	private String OSType;
	
	@NotNull(message = "openSource can't be null")
	private Boolean openSource;


}
