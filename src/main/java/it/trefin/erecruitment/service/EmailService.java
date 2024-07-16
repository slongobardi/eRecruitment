package it.trefin.erecruitment.service;

import java.util.Objects;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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

	public boolean validateEmail(String email) {
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			return true;
		} catch (AddressException e) {
			return false;
		}
	}

	public Response<SimpleMailMessage, Status> inviaEmail(String destinatario, String oggetto, String testo) {
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

	public Response<MimeMessageHelper, Status> confirmEmail(ConfirmToken token, String destinatario) {
		Response<MimeMessageHelper, Status> response = new Response<>();

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(destinatario);
			helper.setSubject("Conferma la tua email");
			helper.setText("<html>" + "<body>" + "<h2>Dear " + token.getUser().getNome() + ",</h2>"
					+ "<br/> We're excited to have you get started. "
					+ "Please click on below link to confirm your account." + "<br/> "
					+ generateConfirmationLink(token.getToken()) + "" + "<br/> Regards,<br/>" + "MFA Registration team"
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
		return "<a href=http://localhost:8080/confirmEmail?token=" + token + ">Confirm Email</a>";
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
