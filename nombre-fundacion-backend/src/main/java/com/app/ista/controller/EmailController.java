package com.app.ista.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@GetMapping("enviarMail")
	public void sendMail (@RequestParam("toEmail") String toEmail, @RequestParam("body") String body,
			@RequestParam("subjetct") String subjetct) throws MessagingException {
		emailService.sendMail(toEmail, body, subjetct);
	}
	
}
