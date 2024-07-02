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

import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteTitoliStudio;
import it.trefin.erecruitment.service.UtenteTitoliStudioService;

@RestController
@RequestMapping("api/utenteTitoliStudio")
public class UtenteTitoliStudioController {

	@Autowired
	private UtenteTitoliStudioService utsService;
	
	 @PostMapping("/add")
	    public Response<UtenteTitoliStudio, Status> inserisciUtenteTitoliStudio(@RequestBody UtenteTitoliStudio utenteTitoliStudio) {
	        return utsService.inserisciUtenteTitoliStudio(utenteTitoliStudio);
	    }

	    @GetMapping("/visualizza/{id}")
	    public Response<UtenteTitoliStudioDto, Status> visualizzaUtenteTitoliStudio(@PathVariable long id) {
	        return utsService.visualizzaUtenteTitoliStudio(id);
	    }

	    @PutMapping("/aggiorna/{id}")
	    public Response<UtenteTitoliStudioDto, Status> aggiornaUtenteTitoliStudio(@RequestBody UtenteTitoliStudio utenteTitoliStudio, @PathVariable Long id) {
	        return utsService.aggiornaUtenteTitoliStudio(utenteTitoliStudio, id);
	    }

	    @DeleteMapping("/elimina/{id}")
	    public Response<UtenteTitoliStudio, Status> eliminaUtenteTitoliStudio(@PathVariable long id) {
	        return utsService.eliminaUtenteTitoliStudio(id);
	    }

	    @GetMapping("/visualizzaTuttiUtenteTitoliStudio")
	    public Response<List<UtenteTitoliStudioDto>, Status> visualizzaTuttiUtenteTitoliStudio() {
	        return utsService.visualizzaTuttiUtenteTitoliStudio();
	    }
}
