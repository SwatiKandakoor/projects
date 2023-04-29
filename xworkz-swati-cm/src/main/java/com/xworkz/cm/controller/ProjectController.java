package com.xworkz.cm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.tomcat.util.http.fileupload.IOUtils;

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
		log.info("running post onSave method..");
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
				log.info("Violation in controller");
				model.addAttribute("errors", violation);
				model.addAttribute("messag", "Data is not Saved");

			}
		}
		return "SignUp";
	}

	@PostMapping("/signIn")
	public String onSignIn(@RequestParam String userId, @RequestParam String password, Model model) {
		log.info("running on sign In page...");
		try {
			String message = this.service.signIn(userId, password);
			if (message == "reset_pwd") {
				model.addAttribute("message", userId);
				return "UpdatePwd";

			} else if (message == "opt_resettime_exp") {
				log.info("Running in time warifying condition");
				model.addAttribute("msgs", "Reset password time expired,reset again");
			} else if (message == "locked") {
				log.info("Acount locked due to wrong password entering 3 times");
				model.addAttribute("message", "You tried maximum retries and your account is locked.");
			} else if (message == "login_success") {
				log.info("User ID and Password is Matching");
				model.addAttribute("message", userId);
				return "LoginSuccess";
			} else if (message == "login_fail") {
				log.info("User ID Or Password is not Matching");
				model.addAttribute("message", "User ID Or Password is not Matching");
//				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SignIn";
	}

	@PostMapping("/resetPwd")
	public String onResetPwd(@RequestParam String email, Model model) {
		log.info("running onReset password method...");
		String message = this.service.resetPwd(email);
		log.info("email has been sent with new password");
		model.addAttribute("message", message);
		return "Forgotpwd";
	}

	@PostMapping("/updatePwd")
	public String onUpdatePwd(@RequestParam String userId, @RequestParam String password, Model model) {
		log.info("running onUpdate password method.. ");
		String msg = this.service.updatePwd(userId, password, false);
		if (msg != null) {
			log.info("Successfully update your password..");
			model.addAttribute("UpdateSuccess", "You have successfully changed your password");
			model.addAttribute("message", userId);
			return "UpdateSuccess";
		}
//	    log.info("invalid password");
//		model.addAttribute("error","invalid Password");
		return "UpdatePwd";
	}

	@GetMapping("/update")
	public String onUpdate(@RequestParam String userId, Model model) {
		log.info("running onupdate.." + userId);
		SignUpDto dto = this.service.findByuserId(userId);
		model.addAttribute("dto", dto);
		return "Updateprofile";

	}

	@PostMapping("/updateProfile")
	public String onUpdateProfile(SignUpDto dto, Model model, @RequestParam("profilePicture") MultipartFile file,
			String userId) throws IOException {
		log.info("running onupdate " + dto);
		log.info("multipartFile" + file);
		log.info(file.getOriginalFilename());
		log.info(file.getContentType());
		log.info("Size of file" + file.getSize());
		log.info("Size of bite" + file.getBytes());
		SignUpDto updateDto = this.service.findByEmail(dto.getEmail());
		if(file!=null) {
			model.addAttribute("message", "Update Profile  success...");
			byte[] bytes = file.getBytes();
			Path path = Paths.get("D:\\Project_image_file" + userId + System.currentTimeMillis() + ".jpg");
			String imageName = path.getFileName().toString();
			Files.write(path, bytes);
			updateDto.setProfilePic(imageName);
			log.info("Image name--" + imageName);
			log.info("Image name in tostring--" + path.toString());
			log.info("Image file name--" + path.getFileName());
		}
		updateDto.setMobile(dto.getMobile());
		updateDto.setUserId(dto.getUserId());
		Set<ConstraintViolation<SignUpDto>> constraintViolations = this.service.validateAndUpdateProfile(updateDto);
		//if (constraintViolations.size() > 0 && file.isEmpty()) {
			model.addAttribute("error", constraintViolations);
			model.addAttribute("error", "please select file");
			log.info("file not uploaded");
			return "Updateprofile";

	//	} 
	//	return "Updateprofile";
	}

	@GetMapping("/download")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName, SignUpDto dto)
			throws IOException {
		log.info("onDownload "+fileName);
		Path path = Paths.get("D:\\highway\\" + dto.getProfilePic());
		path.toFile();
		response.setContentType("image/jpeg");
		File file = new File("D:\\Project_image_file\\" + fileName);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();

	}
}
