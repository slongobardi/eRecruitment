package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.PreferenzaDto;
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.PreferenzaService;

@RestController
@RequestMapping("/api/preferenza")
@CrossOrigin
public class PreferenzaController {
	@Autowired
	private PreferenzaService preferenzaService;
	
	@GetMapping("/byUtente/{id}")
	public Response<List<PreferenzaDto>,Status> findAllByUtente(@PathVariable long id){
		return preferenzaService.findAllByUtente(id);
	}
	
	@PostMapping("/add")
	public Response<PreferenzaDto,Status> add(@RequestBody Preferenza p){
		return preferenzaService.add(p);
	}
}	
