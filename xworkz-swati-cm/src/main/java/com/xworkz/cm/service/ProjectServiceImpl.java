package com.xworkz.cm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.cm.dto.SignUpDto;
import com.xworkz.cm.entity.SignUpEntity;
import com.xworkz.cm.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository repository;

	public ProjectServiceImpl() {
		log.info("create " + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<SignUpDto>> validateAndSave(SignUpDto dto) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<SignUpDto>> constraintViolations = validator.validate(dto);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			System.err.println("constraintViolations exists,return constraints");
			return constraintViolations;
		} else {
			log.info("constraintViolations does not exist,data is good");
			SignUpEntity entity = new SignUpEntity();
			BeanUtils.copyProperties(dto, entity);
			entity.setCreatedBy(dto.getUserId());
			boolean saved = this.repository.save(entity);
			if (saved) {
			//	sendMail(dto.getEmail());
			boolean	sent=sendMail("sahanagn275@gmail.com");
			System.out.println("mail  sent"+sent);
			}
			System.out.println(saved);
			

			System.out.println("Dto" + dto);
			log.info("ENTITY" + entity);
			return Collections.emptySet();
		}
	}

	@Override
	public Integer checkDuplicates(String userId, String email, long mobile) {
		Integer count = this.repository.checkDuplicates(userId, email, mobile);
		SignUpEntity entity = new SignUpEntity();
		SignUpDto dto = new SignUpDto();
		dto.setUserId(entity.getUserId());
		dto.setEmail(entity.getEmail());
		dto.setMobile(entity.getMobile());

		return count;
	}

	@Override
	public boolean sendMail(String email) {
		log.info("running on send mail");
		String portNumber = "587";// 485,587,25
		String hostName = "smtp.office365.com";
		String fromEmail = "sahana.mc26@outlook.com";
		String password = "Swati@2806";
		String to = email;
		// 1)configure the SMTP server details using the java Properties Object
		Properties prop = new Properties();
		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");
		/**
		 * 2)Create Session vth getInstance()&pass properties &Authenticator & Override
		 * getPasswordAuthentication() method Authenticator is a Abstract class
		 */
		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
//3)message class to create  message .*file,*text

		MimeMessage message = new MimeMessage(session);
		// 4)set the to,from,subject,message ->set email important this

		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Registration  completed");
			message.setText("Thanks for registration");
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//5)use transport class to send the mail
			Transport.send(message);
			log.info("mail sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<SignUpDto> signIn(String userId, String password) {
		List<SignUpEntity> entities = this.repository.signIn(userId, password);
		List<SignUpDto> listOfDto = new ArrayList<SignUpDto>();
		for (SignUpEntity entity : entities) {
			SignUpDto dto = new SignUpDto();
			BeanUtils.copyProperties(entity, dto);

			listOfDto.add(dto);

		}

		return listOfDto;

	}

}
