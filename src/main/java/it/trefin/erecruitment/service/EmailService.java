package it.trefin.erecruitment.service;

import java.util.Properties;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Response;

@Service
@Transactional
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender emailSender;

	@Value("${spring.mail.from}")
	private String from;

	public Response<SimpleMailMessage, Status> inviaEmail(String[] destinatario, String oggetto, String testo) {
		SimpleMailMessage msg = new SimpleMailMessage();
		Response<SimpleMailMessage, Status> response = new Response<>();

		try {
			msg.setTo(destinatario);
			msg.setFrom(from);
			msg.setCc("christian.mascolo.3fedin@hotmail.com");
			msg.setSubject(oggetto);
			msg.setText(testo);
			emailSender.send(msg);

			response.setData(msg);
			response.setStatus(Status.OK);
			response.setDescrizione("Email inviata con successo");
			return response;
		} catch (Exception e) {
			logger.error("Error sending email", e);
			// response.setData(msg);
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("Error sending email: " + e.getMessage());
			System.out.println(response.getDescrizione());
			return response;
		}
	}
}
