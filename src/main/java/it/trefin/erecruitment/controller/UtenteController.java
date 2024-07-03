package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.service.UtenteService;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;

@RestController
@RequestMapping("api/utente")
public class UtenteController {

	@Autowired
	private UtenteService uService;

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

	@DeleteMapping("/elimina/{id}")
	public Response<Utente, Status> eliminaUtente(@PathVariable long id) {
		return uService.eliminaUtente(id);
	}

	@GetMapping("/all")
	public Response<List<UtenteDto>, Status> visualizzaTuttiUtenti() {
		return uService.visualizzaTuttiUtenti();
	}
}
