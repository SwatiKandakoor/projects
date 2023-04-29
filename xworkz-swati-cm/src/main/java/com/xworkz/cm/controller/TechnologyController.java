package com.xworkz.cm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.cm.dto.AddTechnologyDto;
import com.xworkz.cm.service.TechnologyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class TechnologyController {
	
	private TechnologyService service;
	
	private List<String>  OStype= Arrays.asList("MS-Windows", "Ubuntu", "Mac OS", "Fedora", "Solaris", "Free BSD",
			"Chrome OS", "CentOS", "Debian", "Deepin", "Linux");

	public TechnologyController() {
		log.info("Running in technology controller");
	}

	@GetMapping("/addTechnology")
	public String onAddTech(Model model) {
		model.addAttribute("type", OStype);
		return "AddTechnology";
	}

	@PostMapping("/addTechnology")
	public String onAddList(String userId,AddTechnologyDto technology, Model model) {
		Set<ConstraintViolation<AddTechnologyDto>> violations = this.service.validateAndAdd(technology);
		if (violations.isEmpty()) {
			log.info("There is no violations can add a technology");
			model.addAttribute("OStype", OStype);
			model.addAttribute("addTechSuccess", "Successfully added the technology : " + technology.getName());
			return "AddTechnology";
		}
		log.info("Violations in the technology, can't add it");
		model.addAttribute("errors", violations);
		return "AddTechnology";
	}

}
