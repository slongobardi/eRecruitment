package it.trefin.erecruitment.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin
public class UtenteController {

	@Autowired
	private UtenteService uService;
	
	private  ObjectMapper objectMapper = new ObjectMapper();

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
	@PutMapping("/modificaSkill/{id}")
	public Response<UtenteDto, Status> modificaSkill(@RequestBody Set<Skill> listaSkill, @PathVariable Long id) {
		return uService.modificaSkill(listaSkill, id);
	}
	
	@PutMapping("/aggiornaCV/{id}")
	public Response<UtenteDto, Status> aggiornaCV(@PathVariable Long id,@RequestParam("cv") MultipartFile cv) {
		return uService.aggiornaCV(id,cv);
	}

	@PutMapping("/aggiungiSkill/{id}")
	public Response<UtenteDto, Status> aggiungiSkill(@PathVariable Long id,@RequestBody Skill s) {
		return uService.aggiungiSkill(id,s);
	}
	@PutMapping("/aggiornaFoto/{id}")
	public Response<UtenteDto, Status> aggiornaFoto(@PathVariable Long id,@RequestBody MultipartFile foto) {
		return uService.aggiornaFoto(id,foto);
	}
	@DeleteMapping("/elimina/{id}")
	public Response<UtenteDto, Status> eliminaUtente(@PathVariable long id) {
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
