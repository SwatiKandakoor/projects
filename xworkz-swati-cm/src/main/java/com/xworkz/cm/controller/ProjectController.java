package com.xworkz.cm.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class ProjectController {

	@Autowired
	private ProjectService service;

	public ProjectController() {
		log.info("create " + this.getClass().getSimpleName());
	}

	@PostMapping("/sign")
	public String onSave(Model model, SignUpDto dto, String userId, String email, long mobile) {
		log.info("running post ");
		Integer count = this.service.checkDuplicates(userId, email, mobile);
		if (count > 0) {
			System.err.println("Duplicates values is thier.");
			model.addAttribute("nonUniqueMessage", "Data is not Saved, Email or Mobile or UserId Already Taken");
			return "SignUp";
		} else {
			Set<ConstraintViolation<SignUpDto>> violation = this.service.validateAndSave(dto);
			if (violation.isEmpty()) {
				log.info(" no Violation in controller");
				model.addAttribute("message", "Data Saved Sucessfully");
				return "SignUp";
			} else {
				model.addAttribute("errors", violation);
				model.addAttribute("messag", "Data is not Saved");

			}
		}
		return "SignUp";
	}

	@PostMapping("/signIn")
	public String onSignIn(@RequestParam String userId, @RequestParam String password, Model model) {
		log.info("running on sign In page");
		List<SignUpDto> list = this.service.signIn(userId, password);
		if (!list.isEmpty()) {
			log.info("User ID and Password is Matching");
			model.addAttribute("list",list);
			return "LoginSuccess";
		}
		model.addAttribute("message", "User ID Or Password is not Matching");

		return "SignIn";
	}

}






































































