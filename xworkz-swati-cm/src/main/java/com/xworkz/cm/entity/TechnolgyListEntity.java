package com.xworkz.cm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "technology" )

public class TechnolgyListEntity {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "language")
	private String language;

	@Column(name = "version")
	private String version;

	@Column(name = "owner")
	private String owner;

	@Column(name = "support_from")
	private String supportFrom;

	@Column(name = "support_to")
	private String supportTo;

	@Column(name = "license")
	private String license;

	@Column(name = "OS_type")
	private String OSType;

	@Column(name = "open_source")
	private Boolean openSource;
}
	


