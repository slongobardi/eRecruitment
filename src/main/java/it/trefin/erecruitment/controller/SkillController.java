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

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.service.SkillService;

@RestController
@RequestMapping("/api/skill")
@CrossOrigin
public class SkillController {

	@Autowired
	private SkillService sService;

	@PostMapping("/add")
	public Response<Skill, Status> inserisciSkill(@RequestBody Skill skill) {
		return sService.inserisciSkill(skill);
	}

	@GetMapping("/visualizza/{id}")
	public Response<SkillDto, Status> visualizzaSkill(@PathVariable long id) {
		return sService.visualizzaSkill(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<SkillDto, Status> aggiornaSkill(@RequestBody Skill skill, @PathVariable Long id) {
		return sService.aggiornaSkill(skill, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Skill, Status> eliminaSkill(@PathVariable long id) {
		return sService.eliminaSkill(id);
	}

	@GetMapping("/all")
	public Response<List<SkillDto>, Status> visualizzaTutteSkills() {
		return sService.visualizzaTutteSkills();
	}
	
//	@GetMapping("/skillCandidatura/{id_candidatura}")
//	public Response<List<SkillDto>,Status> skillCandidatura(@PathVariable long id_candidatura){
//		return sService.skillCandidatura(id_candidatura);
//	}
	
	@GetMapping("/visualizzaSkillsTipologia/{id}")
	public Response<List<SkillDto>,Status> visualizzaSkillsTipologia(@PathVariable long id){
		return sService.visualizzaSkillsTipologia(id);
	}
	
}
