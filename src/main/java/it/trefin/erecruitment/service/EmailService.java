package it.trefin.erecruitment.service;

import java.util.Objects;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.model.ConfirmToken;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;

import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.ConfirmTokenRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
@Transactional
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ConfirmTokenRepository tokenRepo;

	@Autowired
	private UtenteRepository utenteRepo;

	@Value("${spring.mail.from}")
	private String from;

	public Response<MimeMessageHelper, Status> inviaEmail(String[] destinatario, String oggetto, String testo) {
		
		Response<MimeMessageHelper, Status> response = new Response<>();
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(destinatario);
			helper.setFrom(from);
			helper.setCc("christian.mascolo.3fedin@hotmail.com");
			helper.setSubject(oggetto);
			helper.setText(testo,true);

			javaMailSender.send(message);
			response.setData(helper);
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

	public Response<MimeMessageHelper, Status> confirmEmail(ConfirmToken token, String destinatario) {
		Response<MimeMessageHelper, Status> response = new Response<>();

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(destinatario);
			helper.setFrom(from);
			helper.setSubject("Conferma account");
			helper.setText("<html>" 
					+ "<body>" 
					+ "<h2>Benvenut* " + token.getUser().getNome() + ",</h2>"
					+ "Clicca sul link per verificare il tuo account." + "<br/> "
					+ generateConfirmationLink(token.getToken()) +"<br/>"
					+ "La sua prima password per accedere è la seguente: Erecruitment2024!"
					+"<br/> Cordiali saluti,<br/>" + "3F & Edin S.P.A."
					+ "</body>" + "</html>", true);

			javaMailSender.send(message);

			response.setData(helper);
			response.setStatus(Status.OK);
			response.setDescrizione("Email di conferma inviata con successo ");

			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("Errore durante l'invio dell'email: " + e.getMessage());
			return response;
		}
	}

	private String generateConfirmationLink(String token) {
		return "<a href=http://localhost:4200/conferma?token=" + token + " >Conferma account</a>";
	}

	public boolean verifyUser(String token) {
		ConfirmToken emailConfirmationToken = tokenRepo.findByToken(token);
		if (Objects.isNull(emailConfirmationToken) || !token.equals(emailConfirmationToken.getToken())) {
			return false;
		}
		Utente user = emailConfirmationToken.getUser();
		if (Objects.isNull(user)) {
			return false;
		}
		user.setVerified(true);
		utenteRepo.save(user);
		tokenRepo.delete(emailConfirmationToken);
		return true;
	}
}
