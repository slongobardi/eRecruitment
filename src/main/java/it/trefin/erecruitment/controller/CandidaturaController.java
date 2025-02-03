	package it.trefin.erecruitment.controller;
	
	import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
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
		public Response<CandidaturaDto, Status> inserisciCandidatura(@RequestBody CandidaturaDto candidaturaDto) {
			return cService.inserisciCandidatura(candidaturaDto);
		}
		  @PutMapping("/aggiornaLogo/{id}")
		    public Response<CandidaturaDto, Status> aggiornaLogo(@PathVariable Long id, @RequestParam("foto") MultipartFile foto) {
		        return cService.aggiornaLogo(id, foto);
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
		public Response<CandidaturaDto, Status> eliminaCandidatura(@PathVariable long id) {
			return cService.eliminaCandidatura(id);
		}
	
		@GetMapping("/all")
		public Response<List<CandidaturaDto>, Status> visualizzaTutteCandidature() {
			return cService.visualizzaTutteCandidature();
		}
		
		@GetMapping("/allEventi/{id}")
		public Response<List<CandidaturaDto>, Status> visualizzaEventi(@PathVariable long id) {
			return cService.getEventi(id);
		}
		
		@GetMapping("/visualizzaCandidatureAziendali/{id_azienda}")
		public Response<List<CandidaturaDto>,Status> visualizzaCandidatureAziendali(@PathVariable long id_azienda){
			return cService.visualizzaCandidatureAziendali(id_azienda);
		}
		
		@GetMapping("/skillDellaCandidatura/{id_candidatura}")
		public Response<List<SkillDto>,Status> skillDellaCandidatura(@PathVariable long id_candidatura){
			return cService.skillDellaCandidatura(id_candidatura);
		}
		
		@GetMapping("/findCandidatureUtenteAzienda/{idU}/{idA}")
		public Response<Object[],Status> findByUtente(@PathVariable("idU") long idU,@PathVariable("idA") long idA){
			return cService.findByUtente(idU,idA);
		}
		
	
		@PatchMapping("/setDisabilitato/{id_candidatura}")
		public Response<CandidaturaDto, Status> updateDisable(@PathVariable long id_candidatura){
			return cService.updateDescrizione(id_candidatura);
		}
		
	}
