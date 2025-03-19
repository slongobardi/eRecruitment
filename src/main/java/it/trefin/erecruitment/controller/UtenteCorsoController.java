package it.trefin.erecruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.dto.UtenteCorsoDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteCorso;
import it.trefin.erecruitment.service.UtenteCorsoService;

@RestController
@RequestMapping("/api/utenteCorso")
@CrossOrigin
public class UtenteCorsoController {
	@Autowired
	private UtenteCorsoService utenteCorsoService;
	
	@PostMapping("/aggiungi")
	public Response<UtenteCorso, Status> aggiungiUtenteACorso(
	    @RequestParam Long utenteId, @RequestParam Long corsoId) {
	    
	    Response<UtenteCorso, Status> response = utenteCorsoService.iscriviUtenteACorso(utenteId, corsoId);

	    return utenteCorsoService.iscriviUtenteACorso(utenteId, corsoId);
	}

	@GetMapping("/corsi/{utenteId}")
	public List<UtenteCorsoDto> getCorsiPerUtente(@PathVariable Long utenteId) {
	    return utenteCorsoService.visualizzaUtenteCorso(utenteId);
	}
	
	 @GetMapping("/utenti/{corsoId}")
	    public List<UtenteCorsoDto> getUtentiPerCorso(@PathVariable Long corsoId) {
	        return utenteCorsoService.getUtentiIscrittiACorso(corsoId);
	}
	 
	 @GetMapping("/iscritto")
	 public boolean isUtenteIscrittoACorso(@RequestParam Long utenteId, @RequestParam Long corsoId) {
	     return utenteCorsoService.isUtenteIscrittoACorso(utenteId, corsoId);
	 }



	 @DeleteMapping("/rimuoviUtentecorso/{utenteId}/{corsoId}")
	 public ResponseEntity<Response<List<UtenteCorsoDto>, Status>> rimuoviUtenteDaCorso(
	     @PathVariable("utenteId") Long utenteId,
	     @PathVariable("corsoId") Long corsoId) {
	     
	     Response<List<UtenteCorsoDto>, Status> response = utenteCorsoService.eliminaIscrizioneUtente(utenteId, corsoId);
	     
	     if (response.getStatus() == Status.OK) {
	         return ResponseEntity.ok(response);
	     } else {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	     }
	 }

}
