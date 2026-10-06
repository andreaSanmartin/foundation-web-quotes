package com.app.ista.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javamailsender;

	@Value("${app.mail.from}")
	private String fromEmail;
	

	public void sendMail(String toEmail,
						 String body,
						 String subjetct) throws MessagingException {
						 //String attchment) throws MessagingException {
		
		MimeMessage mimeMessage = javamailsender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom(fromEmail);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subjetct);
		
		//FileSystemResource fileSystemResource = new FileSystemResource(new File(attchment));
		//mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javamailsender.send(mimeMessage);
		
		System.out.println("Email enviado correctamente");
	}
}
