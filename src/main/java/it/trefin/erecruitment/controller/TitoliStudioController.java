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

import it.trefin.erecruitment.dto.TitoliStudioDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.service.TitoliStudioService;

@RestController
@RequestMapping("api/titoliStudio")
public class TitoliStudioController {

	@Autowired

	private TitoliStudioService tsService;

	@PostMapping("/add")
	public Response<TitoliStudio, Status> inserisciTitoloStudio(@RequestBody TitoliStudio titoliStudio) {
		return tsService.inserisciTitoloStudio(titoliStudio);
	}

	@GetMapping("/visualizza/{id}")
	public Response<TitoliStudioDto, Status> visualizzaTitoloStudio(@PathVariable long id) {
		return tsService.visualizzaTitoloStudio(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<TitoliStudioDto, Status> aggiornaTitoloStudio(@RequestBody TitoliStudio titoliStudio,
			@PathVariable Long id) {
		return tsService.aggiornaTitoloStudio(titoliStudio, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<TitoliStudio, Status> eliminaTitoloStudio(@PathVariable long id) {
		return tsService.eliminaTitoloStudio(id);
	}

	@GetMapping("/all")
	public Response<List<TitoliStudioDto>, Status> visualizzaTuttiTitoliStudio() {
		return tsService.visualizzaTuttiTitoliStudio();
	}
}
