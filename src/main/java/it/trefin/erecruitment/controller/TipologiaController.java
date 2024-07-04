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

import it.trefin.erecruitment.dto.TipologiaDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Tipologia;
import it.trefin.erecruitment.service.TipologiaService;

@RestController
@RequestMapping("/api/tipologia")
@CrossOrigin
public class TipologiaController {

	@Autowired
	private TipologiaService tService;

	@PostMapping("/add")
	public Response<Tipologia, Status> inserisciTipologia(@RequestBody Tipologia tipologia) {
		return tService.inserisciTipologia(tipologia);
	}

	@GetMapping("/visualizza/{id}")
	public Response<TipologiaDto, Status> visualizzaTipologia(@PathVariable long id) {
		return tService.visualizzaTipologia(id);
	}

	@PutMapping("/aggiorna/{id}")
	public Response<TipologiaDto, Status> aggiornaTipologia(@RequestBody Tipologia tipologia, @PathVariable Long id) {
		return tService.aggiornaTipologia(tipologia, id);
	}

	@DeleteMapping("/elimina/{id}")
	public Response<Tipologia, Status> eliminaTipologia(@PathVariable long id) {
		return tService.eliminaTipologia(id);
	}

	@GetMapping("/all")
	public Response<List<TipologiaDto>, Status> visualizzaTutteTipologie() {
		return tService.visualizzaTutteTipologie();
	}
}
