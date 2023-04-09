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
import com.xworkz.cm.entity.SignUpEntity;
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
		try {
			String message = this.service.signIn(userId, password);
			
			if (message == "reset_pwd") {
				model.addAttribute("message",userId);
				return "UpdatePwd";
			} else if (message == "locked") {
				model.addAttribute("message", "You tried maximum retries and your account is locked.");
			} else if (message == "login_success") {
				log.info("User ID and Password is Matching");
				model.addAttribute("message",userId);
				return "LoginSuccess";
			} else if (message == "login_fail") {
				model.addAttribute("message", "User ID Or Password is not Matching");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SignIn";
	}

	@PostMapping("/resetPwd")
	public String onResetPwd(@RequestParam String email, Model model) {
		String message = this.service.resetPwd(email);
		model.addAttribute("message", message);
		return "Forgotpwd";
	}
	
	@PostMapping("/updatePwd")
	public String onUpdatePwd(@RequestParam String userId,@RequestParam String password) {
		this.service.updatePwd(userId, password, false);
		return "UpdatePwd";
	}
	

}
