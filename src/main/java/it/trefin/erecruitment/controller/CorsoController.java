package it.trefin.erecruitment.controller;

import it.trefin.erecruitment.dto.CorsoDto;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/corso")
@CrossOrigin
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @PostMapping("/add")
    public Response<CorsoDto, Status> inserisciCorso(@RequestBody CorsoDto corsoDto) {
        return corsoService.inserisciCorso(corsoDto);
    }

    @GetMapping("/visualizza/{id}")
    public Response<CorsoDto, Status> visualizzaCorso(@PathVariable long id) {
        return corsoService.visualizzaCorso(id);
    }

    @GetMapping("/all")
    public Response<List<CorsoDto>, Status> visualizzaTuttiCorsi() {
        return corsoService.visualizzaTuttiCorsi();
    }

    @PutMapping("/aggiorna/{id}")
    public Response<CorsoDto, Status> aggiornaCorso(@PathVariable Long id, @RequestBody CorsoDto corsoDto) {
    	 corsoDto.setId(id);
        return corsoService.aggiornaCorso(corsoDto);
    }

    @DeleteMapping("/elimina/{id}")
    public Response<CorsoDto, Status> eliminaCorso(@PathVariable long id) {
        return corsoService.eliminaCorso(id);
    }
    



    
}
