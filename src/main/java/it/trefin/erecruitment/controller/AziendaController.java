package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.AziendaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.AziendaService;

@RestController
@RequestMapping("api/azienda")
public class AziendaController {

	@Autowired
	private AziendaService aService;
	
	   @PostMapping("/add")
	    public Response<Azienda, Status> inserisciAzienda(@RequestBody Azienda azienda) {
	        return aService.inserisciAzienda(azienda);
	    }

	    @GetMapping("/visualizza/{id}")
	    public Response<AziendaDto, Status> visualizzaAzienda(@PathVariable long id) {
	        return aService.visualizzaAzienda(id);
	    }

	    @DeleteMapping("/elimina/{id}")
	    public Response<Azienda, Status> eliminaAzienda(@PathVariable long id) {
	        return aService.eliminaAzienda(id);
	    }

	    @GetMapping("/visualizzaTutteAziende")
	    public Response<List<AziendaDto>, Status> visualizzaTutteAziende() {
	        return aService.visualizzaTutteAziende();
	    }
}
