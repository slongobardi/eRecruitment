package it.trefin.erecruitment.security;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.model.ConfirmToken;
import it.trefin.erecruitment.model.LoginRequest;
import it.trefin.erecruitment.model.LoginResponse;
import it.trefin.erecruitment.model.PasswordChangeRequest;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.ConfirmTokenRepository;
import it.trefin.erecruitment.repository.SchedaCandidatoRepository;
import it.trefin.erecruitment.repository.UtenteRepository;
import it.trefin.erecruitment.service.EmailService;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ConfirmTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Utente user = utenteRepository.findByEmail(request.getEmail());
        System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()));
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = jwtUtil.generateToken(request.getEmail(), user.getRuolo().toString());
        LoginResponse response = new LoginResponse(user.getId(), token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public long register(@RequestBody Utente user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSchedaCandidato(new SchedaCandidato());
        utenteRepository.save(user);

        if (utenteRepository.existsById(user.getId())) {
            sendRegistrationConfirmationEmail(user);
            return user.getId();
        } else {
            return -1;
        }
    }

    @PostMapping("/cambiaPassword")
    public Response<String, Status> cambiaPassword(@RequestBody PasswordChangeRequest request) {
        Utente utente = utenteRepository.findByEmail(request.getEmail());

        Response<String, Status> response = new Response<>();
        if (utente == null) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setData("Utente non trovato");
        } else {
            utente.setPassword(passwordEncoder.encode(request.getNewPassword()));
            utenteRepository.save(utente);
            response.setStatus(Status.OK);
            response.setData("Password modificata con successo");
        }
        return response;
    }

    @PostMapping("/resetPassword/{email}")
    public Response<String, Status> resetPassword(@PathVariable("email") String email) {
        Utente u = utenteRepository.findByEmail(email);
        Response<String, Status> response = new Response<>();
        if (u == null) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setData("Utente non trovato");
        } else {
        	String randomPWd = RandomStringUtils.random(10, true, true);
            u.setPassword(passwordEncoder.encode(randomPWd));
            utenteRepository.save(u);
            response.setStatus(Status.OK);
            response.setData("Password resettata con successo");
            sendPasswordResetEmail(u, randomPWd);
        }
        return response;
    }

    public void sendRegistrationConfirmationEmail(Utente user) {
        // Generate the token
        String tokenValue = getAlphaNumericString(15);
        ConfirmToken emailConfirmationToken = new ConfirmToken();
        emailConfirmationToken.setToken(tokenValue);
        emailConfirmationToken.setTimeStamp(LocalDateTime.now());
        emailConfirmationToken.setUser(user);
        tokenRepository.save(emailConfirmationToken);
        // Send email
        emailService.confirmEmail(emailConfirmationToken, user.getEmail());
    }

    private String getAlphaNumericString(int n) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString = new String(array, Charset.forName("UTF-8"));

        StringBuffer r = new StringBuffer();

        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (n > 0)) {
                r.append(ch);
                n--;
            }
        }

        return r.toString();
    }

    public void sendPasswordResetEmail(Utente user, String newPassword) {
        String emailContent = "<html>" 
                + "<body>" 
                + "<h2>Ciao " + user.getNome() + ",</h2>"
                + "La tua password è stata resettata. La nuova password è: <strong>" + newPassword + "</strong><br/>"
                + "Per favore, accedi al tuo account e cambia la password appena possibile.<br/>"
                + "Cordiali saluti,<br/>" + "3F & Edin S.P.A."
                + "</body>" + "</html>";
        emailService.inviaEmail(new String[] { user.getEmail() }, "Reset della password", emailContent);
    }
}
