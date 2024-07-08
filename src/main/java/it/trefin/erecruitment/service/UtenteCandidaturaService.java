package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.mapper.CandidaturaMapper;
import it.trefin.erecruitment.mapper.UtenteCandidaturaMapper;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;

@Service
public class UtenteCandidaturaService {

	@Autowired
	private UtenteCandidaturaRepository ucRepository;

	public Response<UtenteCandidatura, Status> inserisciUtenteCandidatura(UtenteCandidatura utenteCandidatura) {

		Response<UtenteCandidatura, Status> response = new Response<>();

		try {

			ucRepository.save(utenteCandidatura);
			response.setData(utenteCandidatura);
			response.setStatus(Status.OK);
			response.setDescrizione("utenteCandidatura salvato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciUtenteCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteCandidaturaDto, Status> visualizzaUtenteCandidatura(long id) {

		Response<UtenteCandidaturaDto, Status> response = new Response<>();

		try {

			UtenteCandidatura utenteCandidatura = ucRepository.findById(id).get();

			if (utenteCandidatura != null) {
				UtenteCandidaturaDto dto = UtenteCandidaturaMapper.toDto(utenteCandidatura);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione(" risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna utenteCandidatura trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaUtenteCandidatura in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<UtenteCandidaturaDto, Status> aggiornaUtenteCandidatura(UtenteCandidatura utenteCandidatura,
			Long id) {

		Response<UtenteCandidaturaDto, Status> response = new Response<>();

		try {

			UtenteCandidatura uc = ucRepository.findById(id).get();

			uc.setDataIscrizione(utenteCandidatura.getDataIscrizione());
			uc.setCandidatura(utenteCandidatura.getCandidatura());
			uc.setNota(utenteCandidatura.getNota());
			uc.setStato(utenteCandidatura.getStato());
			uc.setUtente(utenteCandidatura.getUtente());

			ucRepository.save(uc);
			response.setData(UtenteCandidaturaMapper.toDto(uc));
			response.setStatus(Status.OK);
			response.setDescrizione("utenteCandidatura modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtenteCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteCandidatura, Status> eliminaUtenteCandidatura(long id) {

		Response<UtenteCandidatura, Status> response = new Response<>();

		try {

			response.setData(ucRepository.findById(id).get());
			ucRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("UtenteCandidatura eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtenteCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteCandidaturaDto>, Status> visualizzaTuttiUtenteCandidatura() {

		Response<List<UtenteCandidaturaDto>, Status> response = new Response<>();

		try {

			response.setData(
					ucRepository.findAll().stream().map(UtenteCandidaturaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("UtenteCandidatura ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenteCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteCandidaturaDto>, Status> visualizzaByUtente(long id) {

		Response<List<UtenteCandidaturaDto>, Status> response = new Response<>();

		try {

			response.setData(ucRepository.findAllByUtenteId(id).stream().map(UtenteCandidaturaMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("UtenteCandidatura ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenteCandidatura in errore " + e.getMessage());
			return response;

		}
	}

	public Response<List<UtenteCandidaturaDto>, Status> visualizzaByCandidatura(long id) {
		Response<List<UtenteCandidaturaDto>, Status> response = new Response<>();

		try {

			response.setData(ucRepository.findAllByCandidaturaId(id).stream().map(UtenteCandidaturaMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("UtenteCandidatura ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenteCandidatura in errore " + e.getMessage());
			return response;

		}
	}

}
