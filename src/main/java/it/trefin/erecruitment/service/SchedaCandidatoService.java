package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.SchedaCandidatoDto;
import it.trefin.erecruitment.mapper.SchedaCandidatoMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.repository.SchedaCandidatoRepository;

@Service
public class SchedaCandidatoService {
	private Logger logger = LoggerFactory.getLogger(SchedaCandidatoService.class);
    @Autowired
    private SchedaCandidatoRepository scCandidatoRepository;

    public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByPerso() {
        Response<List<SchedaCandidatoDto>, Status> response = new Response<>();
        try {
            List<SchedaCandidato> candidati = scCandidatoRepository.findByPersoTrue();
            List<SchedaCandidatoDto> candidatiDto = candidati.stream()
                    .map(SchedaCandidatoMapper::toDto) 
                    .collect(Collectors.toList());
            response.setData(candidatiDto);
            response.setStatus(Status.OK);
            response.setDescrizione("Candidati perso recuperati con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore nel recupero dei candidati perso: " + e.getMessage());
			logger.warn(e.getMessage());
        }
        return response;
    }

    public Response<List<SchedaCandidatoDto>, Status> getSchedaCandidatiByIngaggiato() {
        Response<List<SchedaCandidatoDto>, Status> response = new Response<>();
        try {
            List<SchedaCandidato> candidati = scCandidatoRepository.findByIngaggiatoTrue();
            List<SchedaCandidatoDto> candidatiDto = candidati.stream()
                    .map(SchedaCandidatoMapper::toDto)
                    .collect(Collectors.toList());
            response.setData(candidatiDto);
            response.setStatus(Status.OK);
            response.setDescrizione("Candidati ingaggiato recuperati con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore nel recupero dei candidati ingaggiato: " + e.getMessage());
			logger.warn(e.getMessage());
        }
        return response;
    }
}
