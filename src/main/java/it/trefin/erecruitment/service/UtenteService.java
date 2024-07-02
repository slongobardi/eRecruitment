package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.mapper.UtenteMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.UtenteRepository;


@Service
public class UtenteService {

	@Autowired
	private UtenteRepository uRepository;

	public Response<Utente, Status> inserisciUtente(Utente utente) {

		Response<Utente, Status> response = new Response<>();

		try {

			uRepository.save(utente);
			response.setData(utente);
			response.setStatus(Status.OK);
			response.setDescrizione("Utente salvato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> visualizzaUtente(long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente utente = uRepository.findById(id).get();

			if (utente != null) {
				UtenteDto dto = UtenteMapper.toDto(utente);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun Utente trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaUtente in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<UtenteDto, Status> aggiornaUtente(Utente utente, Long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente u =  uRepository.findById(id).get();
	
			u.setCellulare(utente.getCellulare());
			u.setCitta(utente.getCitta());
			u.setCognome(utente.getCognome());
			u.setDescrizione(utente.getDescrizione());
			u.setEmail(utente.getEmail());
			u.setIndirizzo(utente.getIndirizzo());
			u.setNome(utente.getNome());
			uRepository.save(u);
			response.setData(UtenteMapper.toDto(u));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<Utente, Status> eliminaUtente(long id) {

		Response<Utente, Status> response = new Response<>();

		try {

			response.setData((uRepository.findById(id).get()));
			uRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("Utente eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteDto>, Status> visualizzaTuttiUtenti() {

		Response<List<UtenteDto>, Status> response = new Response<>();

		try {

			response.setData(uRepository.findAll().stream().map(UtenteMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenti in errore " + e.getMessage());
			return response;

		}

	}
}
