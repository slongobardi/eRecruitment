package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.mapper.UtenteCandidaturaMapper;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;

@Service
public class UtenteCandidaturaService {

	@Autowired
	private UtenteCandidaturaRepository ucRepository;

	@Autowired
	private CandidaturaRepository cRepository;

	public Response<UtenteCandidatura, Status> inserisciUtenteCandidatura(UtenteCandidatura utenteCandidatura) {
	    Response<UtenteCandidatura, Status> response = new Response<>();

	    try {
	        Long utenteId = utenteCandidatura.getUtente().getId();
	        Long candidaturaId = utenteCandidatura.getCandidatura().getId();

	        Optional<UtenteCandidatura> existingCandidatura = ucRepository.findByUtenteIdAndCandidaturaId(utenteId, candidaturaId);

	        if (existingCandidatura.isPresent()) {
	            response.setStatus(Status.SYSTEM_ERROR);
	            response.setDescrizione("Hai già effettuato una candidatura per questa posizione.");
	        } else {
				Candidatura c = cRepository.findById(utenteCandidatura.getCandidatura().getId()).get();

	            c.setNumeroCandidati(c.getNumeroCandidati() + 1);
	            cRepository.save(c);

	            ucRepository.save(utenteCandidatura);

	            response.setData(utenteCandidatura);
	            response.setStatus(Status.OK);
	            response.setDescrizione("UtenteCandidatura salvato con successo.");
	        }

	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("Errore durante l'inserimento dell'UtenteCandidatura: " + e.getMessage());
	    }

	    return response;
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

	public Response<List<UtenteCandidaturaDto>, Status> eliminaUtenteCandidatura(long id, long idCandidatura) {

		Response<List<UtenteCandidaturaDto>, Status> response = new Response<>();
		try {
			Candidatura c = cRepository.findById(idCandidatura).get();
			c.setNumeroCandidati(c.getNumeroCandidati() - 1);
			ucRepository.deleteById(id);
			response.setData(
					ucRepository.findAll().stream().map(UtenteCandidaturaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("eliminata con successo.");
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
