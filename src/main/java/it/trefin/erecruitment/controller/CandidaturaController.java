package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.CandidaturaService;

@RestController
@RequestMapping("api/candidatura")
@CrossOrigin
public class CandidaturaController {

	@Autowired
	private CandidaturaService cService;

	@PostMapping("/add")
	public Response<Candidatura, Status> inserisciCandidatura(@RequestBody CandidaturaDto candidaturaDto) {
		return cService.inserisciCandidatura(candidaturaDto);
	}


	@GetMapping("/visualizza/{id}")
	public Response<CandidaturaDto, Status> visualizzaCandidatura(@PathVariable long id) {
		return cService.visualizzaCandidatura(id);
	}

	@PutMapping("/aggiorna")
	public Response<CandidaturaDto, Status> aggiornaCandidatura(@RequestBody CandidaturaDto candidatura) {
		return cService.aggiornaCandidatura(candidatura);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Candidatura, Status> eliminaCandidatura(@PathVariable long id) {
		return cService.eliminaCandidatura(id);
	}

	@GetMapping("/all")
	public Response<List<CandidaturaDto>, Status> visualizzaTutteCandidature() {
		return cService.visualizzaTutteCandidature();
	}
	
	@GetMapping("/visualizzaCandidatureAziendali/{id_azienda}")
	public Response<List<CandidaturaDto>,Status> visualizzaCandidatureAziendali(@PathVariable long id_azienda){
		return cService.visualizzaCandidatureAziendali(id_azienda);
	}
	
	@GetMapping("/skillDellaCandidatura/{id_candidatura}")
	public Response<List<SkillDto>,Status> skillDellaCandidatura(@PathVariable long id_candidatura){
		return cService.skillDellaCandidatura(id_candidatura);
	}
}
