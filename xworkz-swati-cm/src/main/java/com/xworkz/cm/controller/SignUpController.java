package com.xworkz.cm.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.service.SignUpService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class SignUpController {
	
	@Autowired
	private SignUpService service;
	public SignUpController() {
		log.info("create " + this.getClass().getSimpleName());
	}
    

	
	@PostMapping("/sign")
	public String onSave(Model model,SignUpDto dto) {
			log.info("running post ");
			Set<ConstraintViolation<SignUpDto>> violation = this.service.validateAndSave(dto);
			if (violation.isEmpty()) {
				log.info(" no Violation in controller");
				model.addAttribute("message", "Data Saved Sucessfully");
				return "SignUp";
			}
			model.addAttribute("errors", violation);
			model.addAttribute("messag", "Data is not Saved");

		return "SignUp";
	}

	}


