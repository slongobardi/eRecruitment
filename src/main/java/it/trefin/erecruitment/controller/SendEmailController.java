package it.trefin.erecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.service.EmailService;

@CrossOrigin
@RestController
@RequestMapping("/api/sendEmail")
public class SendEmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/inviaEmail")
	public Response<SimpleMailMessage, Status> inviaEmail(@RequestParam String [] destinatario,
			@RequestParam String oggetto, @RequestParam String testo) {

		return emailService.inviaEmail(destinatario, oggetto, testo);

	}
}
