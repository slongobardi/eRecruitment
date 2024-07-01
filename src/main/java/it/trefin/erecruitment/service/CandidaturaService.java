package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.mapper.CandidaturaMapper;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.repository.CandidaturaRepository;

@Service
public class CandidaturaService {

	@Autowired
	private CandidaturaRepository cRepository;

	public Response<Candidatura, Status> inserisciCandidatura(Candidatura candidatura) {

		Response<Candidatura, Status> response = new Response<>();

		try {

			cRepository.save(candidatura);
			response.setData(candidatura);
			response.setStatus(Status.OK);
			response.setDescrizione("candidatura salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<CandidaturaDto, Status> visualizzaUtente(long id) {

		Response<CandidaturaDto, Status> response = new Response<>();

		try {

			Candidatura candidatura = cRepository.findById(id).get();

			if (candidatura != null) {
				SchedaUtenteDTO dto = CandidaturaMapper.toDto(candidatura);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione(" risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna candidatura trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatura in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<CandidaturaDto, Status> aggiornaUtente(Candidatura candidatura, Long id) {

		Response<CandidaturaDto, Status> response = new Response<>();

		try {

			Candidatura c = cRepository.findById(id).get();
			
			response.setData(SchedaUtenteMapper.toDto(schedaUtente));
			response.setStatus(Status.OK);
			response.setDescrizione("SchedaUtente modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<SchedaUtente, Status> eliminaUtente(long id) {

		Response<SchedaUtente, Status> response = new Response<>();

		try {

			response.setData(schedaUtenteRepository.getByUtenteId(id));
			schedaUtenteRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("schedaUtente eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<SchedaUtenteDTO>, Status> visualizzaTuttiUtenti() {

		Response<List<SchedaUtenteDTO>, Status> response = new Response<>();

		try {

			response.setData(schedaUtenteRepository.findAll().stream().map(SchedaUtenteMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("SchedeUtente ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenti in errore " + e.getMessage());
			return response;

		}

	}
}
