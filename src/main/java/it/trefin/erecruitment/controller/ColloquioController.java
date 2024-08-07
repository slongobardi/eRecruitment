package it.trefin.erecruitment.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Feedback;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.ColloquioService;

@RestController
@RequestMapping("api/colloquio")
@CrossOrigin
public class ColloquioController {

	@Autowired
	private ColloquioService cService;

	@PostMapping("/add")
	public Response<Colloquio, Status> inserisciColloquio(@RequestBody Colloquio colloquio) {
		return cService.inserisciColloquio(colloquio);
	}

	@GetMapping("/visualizza/{id}")
	public Response<ColloquioDto, Status> visualizzaColloquio(@PathVariable long id) {
		return cService.visualizzaColloquio(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<ColloquioDto, Status> aggiornaColloquio(@RequestBody Colloquio colloquio, @PathVariable Long id) {
		return cService.aggiornaColloquio(colloquio, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Colloquio, Status> eliminaColloquio(@PathVariable long id) {
		return cService.eliminaColloquio(id);
	}

	@GetMapping("/all")
	public Response<List<ColloquioDto>, Status> visualizzaTuttiColloqui() {
		return cService.visualizzaTuttiColloqui();
	}

	@GetMapping("/colloquiCandidatura/{idCandidatura}/{idUtente}")
	public Response<List<ColloquioDto>, Status> colloquiCandidatura(@PathVariable long idCandidatura,
			@PathVariable long idUtente) {
		return cService.colloquiCandidatura(idCandidatura, idUtente);
	}

	/*
	 * @GetMapping("/colloquioUtenteCand/{idUtente}/{idCandidatura") public
	 * Response<List<ColloquioDto>, Status> colloquioUtenteCand(@PathVariable long
	 * idCandidatura) { return cService.colloquioUtenteCand(idCandidatura); }
	 */

	@PatchMapping("/updateFeedback/{feedback}/{id}")
	public Response<ColloquioDto, Status> updateFeedback(@PathVariable("feedback") Feedback f,
			@PathVariable("id") long id) {
		return cService.updateFeedback(f,id);
	}
	
	@PatchMapping("/updateDescrizione/{id}")
	public Response<ColloquioDto, Status> updateDescrizione(@RequestBody String descrizione,
			@PathVariable("id") long id) {
		return cService.updateDescrizione(descrizione,id);
	}
	
	@GetMapping("/filterByDate")
	public Response<List<ColloquioDto>,Status> filterByDate(@RequestParam("start") String start,@RequestParam("end") String end){
		return cService.filterByDate(start,end);
	}
	
	@GetMapping("/totalFeedback/{id}/{startDate}/{endDate}")
	public Response<Object[],Status> totalFeedback(@PathVariable("id") int id,@PathVariable("startDate") Date startDate,@PathVariable("endDate") Date endDate){
		return cService.totalFeedback(id, startDate, endDate);
	}
	
	@GetMapping("/report/{id}/{startDate}/{endDate}")
	public Response<Object[],Status> report(@PathVariable("id") int id,@PathVariable("startDate") Date startDate,@PathVariable("endDate") Date endDate){
		return cService.report(id, startDate, endDate);
	}
}