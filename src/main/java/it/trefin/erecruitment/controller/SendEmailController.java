package it.trefin.erecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/inviaEmail")
	public Response<String, Status> inviaEmail(@RequestParam String [] destinatario,
			@RequestParam String oggetto, @RequestBody String testo) {

		return emailService.inviaEmail(destinatario, oggetto, testo);

	}
	
	@PostMapping("/inviaEmailAdmin")
	public Response<String, Status> inviaEmailAdmin(
	        @RequestParam String[] destinatario,
	        @RequestParam String oggetto,
	        @RequestBody String testo,
	        @RequestParam(required = false) String[] bcc) {

	    return emailService.inviaEmailAdmin(destinatario, oggetto, testo, bcc);
	}

	
	@GetMapping("/confirmEmail")
	public boolean confirmEmail(@RequestParam("token") String token) {
		return emailService.verifyUser(token);
	}
}
