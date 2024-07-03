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

import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.service.UtenteCandidaturaService;

@RestController
@RequestMapping("/api/utenteCandidatura")
public class UtenteCandidaturaController {

	@Autowired
	private UtenteCandidaturaService ucService;

	@PostMapping("/add")
	public Response<UtenteCandidatura, Status> inserisciUtenteCandidatura(
			@RequestBody UtenteCandidatura utenteCandidatura) {
		return ucService.inserisciUtenteCandidatura(utenteCandidatura);
	}

	@GetMapping("/visualizza/{id}")
	public Response<UtenteCandidaturaDto, Status> visualizzaUtenteCandidatura(@PathVariable long id) {
		return ucService.visualizzaUtenteCandidatura(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<UtenteCandidaturaDto, Status> aggiornaUtenteCandidatura(
			@RequestBody UtenteCandidatura utenteCandidatura, @PathVariable Long id) {
		return ucService.aggiornaUtenteCandidatura(utenteCandidatura, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<UtenteCandidatura, Status> eliminaUtenteCandidatura(@PathVariable long id) {
		return ucService.eliminaUtenteCandidatura(id);
	}

	@GetMapping("/all")
	public Response<List<UtenteCandidaturaDto>, Status> visualizzaTuttiUtenteCandidatura() {
		return ucService.visualizzaTuttiUtenteCandidatura();
	}
}
