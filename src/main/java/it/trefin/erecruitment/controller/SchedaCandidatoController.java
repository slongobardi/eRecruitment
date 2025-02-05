package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.SchedaCandidatoDto;
import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
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
	
	@PatchMapping("/ingaggia")
	public Response<String, Status> ingaggiaUtente(@RequestParam long id, @RequestParam long idAzienda){
		return schedaCandidatoService.ingaggiaUtente(id, idAzienda);
	}
	@PatchMapping("/ingaggiaEvento")
	public Response<String, Status> ingaggiaUtenteEvento(@RequestParam long id, @RequestParam long idAzienda,@RequestParam String nota){
		return schedaCandidatoService.ingaggiaUtenteEvento(id, idAzienda,nota);
	}
	
	@PatchMapping("/perso")
	public Response<String, Status> persoUtente(@RequestParam long id, @RequestParam String nota, @RequestParam long idAzienda){
		return schedaCandidatoService.persoUtente(id, nota,idAzienda);
	}
	
	@PatchMapping("/pickingList")
	public Response<String, Status> pickingListUtente(@RequestParam long id, @RequestParam long idAzienda){
		return schedaCandidatoService.pickingList(id,idAzienda);
	}
	
	@GetMapping("/byUtenteAndAzienda/{idU}/{idA}")
	public Response<SchedaCandidatoDto, Status> getSchedeByUtenteAndAzienda(@PathVariable("idU") long idU,@PathVariable("idA") long idA) {
		return schedaCandidatoService.getSchedaCandidatiByAziendaAndUtente(idU,idA);
	}
	@GetMapping("/getPickingList/{idA}")
	public Response<List<UtenteCandidaturaDto>, Status> getPickingList(@PathVariable("idA") long idA) {
		return schedaCandidatoService.getPickingList(idA);
	}
}
