package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.AziendaDto;
import it.trefin.erecruitment.mapper.AziendaMapper;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.AziendaRepository;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class AziendaService {
	private Logger logger = LoggerFactory.getLogger(AziendaService.class);

	@Autowired
	private AziendaRepository aRepository;
	@Autowired
	private UtenteRepository uRepository;
	@Autowired
	private CandidaturaRepository cRepository;

	public Response<Azienda, Status> inserisciAzienda(Azienda azienda) {

		Response<Azienda, Status> response = new Response<>();

		try {

			aRepository.save(azienda);
			response.setData(azienda);
			response.setStatus(Status.OK);
			response.setDescrizione("Azienda salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciAzienda in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<AziendaDto, Status> visualizzaAzienda(long id) {

		Response<AziendaDto, Status> response = new Response<>();

		try {

			Azienda azienda = aRepository.findById(id).get();

			if (azienda != null) {
				AziendaDto dto = AziendaMapper.toDto(azienda);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione(" risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun Azienda trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaAzienda in errore: " + e.getMessage());
			logger.warn(e.getMessage());
		}

		return response;
	}

	public Response<Azienda, Status> eliminaAzienda(long id) {

		Response<Azienda, Status> response = new Response<>();

		try {

			response.setData(aRepository.findById(id).get());
			aRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("Azienda eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaAzienda in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<AziendaDto>, Status> visualizzaTutteAziende() {

		Response<List<AziendaDto>, Status> response = new Response<>();

		try {

			response.setData(aRepository.findAll().stream().map(AziendaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Azienda ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteAzienda in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<AziendaDto, Status> getByNome(String nome) {
		Response<AziendaDto, Status> response = new Response<>();

		try {
			Azienda a = aRepository.findByNome(nome);

			response.setData(AziendaMapper.toDto(a));
			response.setStatus(Status.OK);
			response.setDescrizione("Azienda ritornata con successo.");

			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("getByNome in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;
		}

	}

	public void aggiornaAzienda(AziendaDto azienda) {
		List<Utente> listaUtenti = new ArrayList<>();
		List<Candidatura> listaCandidature = new ArrayList<>();
		for (long idUtente : azienda.getUtenti()) {
			listaUtenti.add(this.uRepository.getReferenceById(idUtente));
		}
		for (long idCandidatura : azienda.getListaCandidature()) {
			listaCandidature.add(this.cRepository.getReferenceById(idCandidatura));
		}
		Azienda aziendaAggiornata = AziendaMapper.toEntity(azienda, listaUtenti, listaCandidature);
		this.aRepository.save(aziendaAggiornata);

	}

}
