package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.mapper.UtenteCandidaturaMapper;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.repository.AziendaRepository;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.SchedaCandidatoRepository;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class UtenteCandidaturaService {
	private Logger logger = LoggerFactory.getLogger(UtenteCandidaturaService.class);
	@Autowired
	private UtenteCandidaturaRepository ucRepository;
	@Autowired
	private CandidaturaRepository cRepository;
	@Autowired
	private SchedaCandidatoRepository scRepository;
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private AziendaRepository aRepository;

	public Response<UtenteCandidatura, Status> inserisciUtenteCandidatura(UtenteCandidatura utenteCandidatura,
			long idAzienda, long idUtente) {
		Response<UtenteCandidatura, Status> response = new Response<>();

		try {
			Long utenteId = utenteCandidatura.getUtente().getId();
			Long candidaturaId = utenteCandidatura.getCandidatura().getId();
			Azienda azienda = this.aRepository.getReferenceById(idAzienda);
			List<SchedaCandidato> schede = scRepository.findAllByUtenteId(idUtente);
			Optional<UtenteCandidatura> existingCandidatura = ucRepository.findByUtenteIdAndCandidaturaId(utenteId,
					candidaturaId);

			if (existingCandidatura.isPresent()) {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Hai già effettuato una candidatura per questa posizione.");
			} else {
				Candidatura c = cRepository.findById(utenteCandidatura.getCandidatura().getId()).get();
				if (schede != null && findScheda(schede, azienda.getId()) == -1) {
					scRepository
							.save(SchedaCandidato.defaultScheda(azienda, utenteRepository.findById(idUtente).get()));
				}
				if (schede == null) {
					scRepository
							.save(SchedaCandidato.defaultScheda(azienda, utenteRepository.findById(idUtente).get()));
				}
				c.setNumeroCandidati(c.getNumeroCandidati() + 1);
				cRepository.save(c);
				utenteCandidatura.setDisabilitato(false);
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
			logger.warn(e.getMessage());
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
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteCandidaturaDto>, Status> eliminaUtenteCandidatura(long idUtente, long idCandidatura) {

		Response<List<UtenteCandidaturaDto>, Status> response = new Response<>();
		try {
			Candidatura c = cRepository.findById(idCandidatura).get();
			c.setNumeroCandidati(c.getNumeroCandidati() - 1);
			ucRepository.deleteById(idUtente);
			response.setData(
					ucRepository.findAll().stream().map(UtenteCandidaturaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtenteCandidatura in errore " + e.getMessage());
			logger.warn(e.getMessage());
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
			logger.warn(e.getMessage());
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
			logger.warn(e.getMessage());
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
			logger.warn(e.getMessage());
			return response;

		}
	}

	private int findScheda(List<SchedaCandidato> schede, long idAzienda) {
		for (SchedaCandidato sc : schede) {
			if (sc.getAzienda().getId() == idAzienda) {
				return 0;
			}
		}
		return -1;
	}

}
