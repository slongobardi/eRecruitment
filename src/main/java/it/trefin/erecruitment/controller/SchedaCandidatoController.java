package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/picking")
	public Response<List<SchedaCandidatoDto>, Status> getCandidatiByPicking() {
		return schedaCandidatoService.getSchedaCandidatiByPicking();
	}

	@GetMapping("/perso")
	public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByPerso() {
		return schedaCandidatoService.getSchedaCandidatiByPerso();
	}

	@GetMapping("/ingaggiato")
	public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByIngaggiato() {
		return schedaCandidatoService.getSchedaCandidatiByIngaggiato();
	}
}
