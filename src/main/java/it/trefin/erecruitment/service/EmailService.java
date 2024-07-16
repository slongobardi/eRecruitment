package it.trefin.erecruitment.service;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;

@Service
@Transactional
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.from}")
	private String from;

	

	public Response<SimpleMailMessage, Status> inviaEmail(String [] destinatario, String oggetto, String testo) {
		SimpleMailMessage msg = new SimpleMailMessage();
		Response<SimpleMailMessage, Status> response = new Response<>();
		try {
			msg.setTo(destinatario);
			msg.setFrom(from);
			msg.setCc("christian.mascolo.3fedin@hotmail.com");
			msg.setSubject(oggetto);
			msg.setText(testo);

			javaMailSender.send(msg);
			response.setData(msg);
			response.setStatus(Status.OK);
			response.setDescrizione("Email inviata con successo ");
			return response;
		} catch (Exception e) {
			logger.error("Errore durante l'invio dell'email: {}", e.getMessage());
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("Errore durante l'invio dell'email: " + e.getMessage());
			return response;
		}
	}
}
