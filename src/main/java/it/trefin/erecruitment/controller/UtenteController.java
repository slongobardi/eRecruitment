package it.trefin.erecruitment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin
public class UtenteController {

	@Autowired
	private UtenteService uService;
	
	private  ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/add")
	public Response<Utente, Status> inserisciUtente(@RequestBody Utente utente) {
		return uService.inserisciUtente(utente);
	}

	@GetMapping("/visualizza/{id}")
	public Response<UtenteDto, Status> visualizzaUtente(@PathVariable long id) {
		return uService.visualizzaUtente(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<UtenteDto, Status> aggiornaUtente(@RequestBody Utente utente, @PathVariable Long id) {
		return uService.aggiornaUtente(utente, id);
	}
	@PutMapping("/aggiornaDescrizione/{id}")
	public Response<UtenteDto, Status> aggiornaDescrizioneUtente(@RequestBody Utente utente, @PathVariable Long id) {
		return uService.aggiornaDescrizioneUtente(utente, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Utente, Status> eliminaUtente(@PathVariable long id) {
		return uService.eliminaUtente(id);
	}

	@GetMapping("/all")
	public Response<List<UtenteDto>, Status> visualizzaTuttiUtenti() {
		return uService.visualizzaTuttiUtenti();
	}

	@GetMapping("/skillUtente/{id}")
	public Response<List<SkillDto>, Status> skillUtente(@PathVariable long id) {
		return uService.skillUtente(id);
	}

	@GetMapping("/titoliUtente/{id}")
	public Response<List<UtenteTitoliStudioDto>, Status> titoliUtente(@PathVariable long id) {
		return uService.titoliUtente(id);
	}
	
	@GetMapping("/allNotUser")
	public Response<List<UtenteDto>,Status> notUser(){
		return uService.getAllNotUser();
	}

	@PutMapping("/modificaColloquio/{idCandidato}")
	public Response<Utente,Status> modificaColloquio(@PathVariable long idCandidato,@RequestBody Map<Object,Object> c){
		//return uService.modificaColloquio(idCandidato,idColloquio);
		Object colloquio = c.get("colloquio");         
		Object simpleMailMessage = c.get("simpleMailMessage");

		Colloquio coll = objectMapper.convertValue(c.get("colloquio"), Colloquio.class);
		SimpleMailMessage simpleM = objectMapper.convertValue(c.get("simpleMailMessage"), SimpleMailMessage.class);

		return uService.modificaColloquio(idCandidato,coll, simpleM);
		
	}
}
