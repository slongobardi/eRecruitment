package it.trefin.erecruitment.security;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.model.ConfirmToken;
import it.trefin.erecruitment.model.LoginRequest;
import it.trefin.erecruitment.model.LoginResponse;
import it.trefin.erecruitment.model.PasswordChangeRequest;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.ConfirmTokenRepository;
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

	private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		Utente user = utenteRepository.findByEmail(request.getEmail());
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
		utenteRepository.save(user);

		if (utenteRepository.existsById(user.getId())) {
			sendRegistrationConfirmationEmail(user);
			return user.getId();
		} else {
			return -1;
		}
	}

	@PostMapping("/cambiaPassword")
	public ResponseEntity<String> cambiaPassword(@RequestBody PasswordChangeRequest request) {
		Utente utente = utenteRepository.findByEmail(request.getEmail());

		if (utente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		if (!passwordEncoder.matches(request.getOldPassword(), utente.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid old password");
		}

		utente.setPassword(passwordEncoder.encode(request.getNewPassword()));
		utenteRepository.save(utente);
		return ResponseEntity.ok("Password changed successfully");
	}

	public void sendRegistrationConfirmationEmail(Utente user) {
		// Generate the token
		String tokenValue = new String(Base64.getEncoder().encodeToString(DEFAULT_TOKEN_GENERATOR.generateKey()));
		ConfirmToken emailConfirmationToken = new ConfirmToken();
		emailConfirmationToken.setToken(tokenValue);
		emailConfirmationToken.setTimeStamp(LocalDateTime.now());
		emailConfirmationToken.setUser(user);
		tokenRepository.save(emailConfirmationToken);
		// Send email
		emailService.confirmEmail(emailConfirmationToken,user.getEmail());
	}

}
