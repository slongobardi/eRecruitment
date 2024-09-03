package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.SchedaCandidatoDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.SchedaCandidatoService;

@RestController
@RequestMapping("/api/schedacandidato")
@CrossOrigin
public class SchedaCandidatoController {

	@Autowired
	private SchedaCandidatoService schedaCandidatoService;

	@GetMapping("/perso")
	public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByPerso() {
		return schedaCandidatoService.getSchedaCandidatiByPerso();
	}

	@GetMapping("/ingaggiato")
	public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByIngaggiato() {
		return schedaCandidatoService.getSchedaCandidatiByIngaggiato();
	}
	
	@GetMapping("/byUtenteAndAzienda/{idU}/{idA}")
	public Response<List<SchedaCandidatoDto>, Status> getSchedeByUtenteAndAzienda(@PathVariable("idU") long idU,@PathVariable("idA") long idA) {
		return schedaCandidatoService.getSchedaCandidatiByAziendaAndUtente(idU,idA);
	}
}
