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

import it.trefin.erecruitment.dto.EsperienzaDto;
import it.trefin.erecruitment.model.Esperienza;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.EsperienzaService;

@RestController
@RequestMapping("/api/esperienza")
@CrossOrigin
public class EsperienzaController {

	@Autowired
	private EsperienzaService eService;
	
	
	@PostMapping("/add")
	public Response<Esperienza, Status> inserisciColloquio(@RequestBody Esperienza esperienza) {
		return eService.inserisciEsperienza(esperienza);
	}

	@GetMapping("/visualizza/{id}")
	public Response<EsperienzaDto, Status> visualizzaEsperienza(@PathVariable long id) {
		return eService.visualizzaEsperienza(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<EsperienzaDto, Status> aggiornaEsperienza(@RequestBody Esperienza esperienza, @PathVariable Long id) {
		return eService.aggiornaEsperienza(esperienza, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Esperienza, Status> eliminaEsperienza(@PathVariable long id) {
		return eService.eliminaEsperienza(id);
	}

	@GetMapping("/all")
	public Response<List<EsperienzaDto>, Status> visualizzaTutteEsperienze() {
		return eService.visualizzaTutteEsperienze();
	}
}
