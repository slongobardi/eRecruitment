package it.trefin.erecruitment.service;

import java.security.SecureRandom;
import java.util.Objects;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.model.ConfirmToken;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.ConfirmTokenRepository;
import it.trefin.erecruitment.repository.UtenteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12;
    private SecureRandom random = new SecureRandom();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Aggiunto il password encoder

    public Response<String, Status> inviaEmail(String[] destinatario, String oggetto, String testo) {
        Response<String, Status> response = new Response<>();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(destinatario);
            helper.setFrom(from);
            helper.setSubject(oggetto);
            helper.setText(testo, true);
            
            javaMailSender.send(message);
            response.setData("Email inviata con successo");
            response.setStatus(Status.OK);
            response.setDescrizione("Email inviata con successo ");
            return null;
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
            // Generare una password casuale
            String randomPassword = generateRandomPassword()+"E-re24";
            Utente user = token.getUser();
            String encryptedPassword = passwordEncoder.encode(randomPassword); // Cifrare la password
            user.setPassword(encryptedPassword);
            utenteRepo.save(user);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(destinatario);
            helper.setFrom(from);
            helper.setSubject("Conferma account");
            helper.setText("<html>" 
                    + "<body>" 
                    + "con questo messaggio, ti confermiamo la creazione del tuo account sul nostro portale." + "<br/> "
                    + "Per favore, fai click sul link qua sotto per attivare l'account e ricordati di cambiare la password al primo accesso " + "<br/> "
                    + generateConfirmationLink(token.getToken()) + "<br/>"
                    + "La tua prima password per accedere è la seguente: " + randomPassword + "<br/>"
                    + "Cordiali saluti,<br/>" + "Talent Acquisition, <br/>" + "Trefin Group Company"
                    + "</body>" + "</html>", true);

            javaMailSender.send(message);

            response.setData(helper);
            response.setStatus(Status.OK);
            response.setDescrizione("Email di conferma inviata con successo ");

            return response;
        } catch (Exception e) {
            logger.error("Errore durante l'invio dell'email: {}", e.getMessage());
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'invio dell'email: " + e.getMessage());
            return response;
        }
    }

    
    
    public Response<MimeMessageHelper, Status> confirmEmailEvent(ConfirmToken token, String destinatario,Long idEvento) {
        Response<MimeMessageHelper, Status> response = new Response<>();

        try {
            // Generare una password casuale
            String randomPassword = generateRandomPassword()+"E-re24";
            Utente user = token.getUser();
            String encryptedPassword = passwordEncoder.encode(randomPassword); // Cifrare la password
            user.setPassword(encryptedPassword);
            utenteRepo.save(user);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(destinatario);
            helper.setFrom(from);
            helper.setSubject("Conferma account");
            helper.setText("<html>" 
                    + "<body>" 
                    + "<h2>Gentile " + token.getUser().getNome() + ",</h2>"
                    + "con questo messaggio, ti confermiamo la creazione del tuo account sul nostro portale." + "<br/> "
                    + "Per favore, fai click sul link qua sotto per attivare l'account e ricordati di cambiare la password al primo accesso " + "<br/> "
                    + generateConfirmationLinkEvent(token.getToken(),idEvento) + "<br/>"
                    + "La tua prima password per accedere è la seguente: " + randomPassword + "<br/>"
                    + "Cordiali saluti,<br/>" + "Talent Acquisition, <br/>" + "Trefin Group Company "
                    + "</body>" + "</html>", true);

            javaMailSender.send(message);

            response.setData(helper);
            response.setStatus(Status.OK);
            response.setDescrizione("Email di conferma inviata con successo ");

            return response;
        } catch (Exception e) {
            logger.error("Errore durante l'invio dell'email: {}", e.getMessage());
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'invio dell'email: " + e.getMessage());
            return response;
        }
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        
        return password.toString();
    }

    private String generateConfirmationLink(String token) {
        return "<a href=http://192.168.200.248:8080/e-recruitment/#/login?token=" + token + " >Conferma account</a>";
    }
    private String generateConfirmationLinkEvent(String token,Long idEvento) {
        return "<a href=http://192.168.200.248:8080/e-recruitment/#/loginEvento/"+idEvento+"?token=" + token + " >Conferma account</a>";
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
