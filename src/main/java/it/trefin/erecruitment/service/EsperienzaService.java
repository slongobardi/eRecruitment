package it.trefin.erecruitment.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.EsperienzaDto;
import it.trefin.erecruitment.mapper.EsperienzaMapper;
import it.trefin.erecruitment.model.Esperienza;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.EsperienzaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class EsperienzaService {
	private Logger logger = LoggerFactory.getLogger(EsperienzaService.class);
	@Autowired
	private EsperienzaRepository eRepository;
	@Autowired
	private UtenteRepository uRepository;

	
	
	public Response<Esperienza, Status> inserisciEsperienza(long id,Esperienza esperienza) {

		Response<Esperienza, Status> response = new Response<>();

		Utente u = uRepository.findById(id).get();
		
		
		
		try {
			esperienza.setUtente(u);
			eRepository.save(esperienza);
			u.getEsperienze().add(esperienza);
			u.setEsperienze(u.getEsperienze());
			uRepository.save(u);
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienza salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciEsperienza in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<EsperienzaDto, Status> visualizzaEsperienza(long id) {

		Response<EsperienzaDto, Status> response = new Response<>();

		try {

			Esperienza esperienza = eRepository.findById(id).get();

			if (esperienza != null) {
				EsperienzaDto dto = EsperienzaMapper.toDto(esperienza);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun esperienza trovata con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaEsperienza in errore: " + e.getMessage());
			logger.warn(e.getMessage());
		}

		return response;
	}

	public Response<EsperienzaDto, Status> aggiornaEsperienza(Esperienza esperienza, Long id) {

		Response<EsperienzaDto, Status> response = new Response<>();

		try {

			Esperienza e = eRepository.findById(id).get();
			
			e.setNome(esperienza.getNome());
			e.setDescrizione(esperienza.getDescrizione());



			eRepository.save(e);
			response.setData(EsperienzaMapper.toDto(e));
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienza modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaEsperienza in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<Esperienza, Status> eliminaEsperienza(long id) {

		Response<Esperienza, Status> response = new Response<>();

		try {

			response.setData((eRepository.findById(id).get()));
			eRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienza eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaEsperienza in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<EsperienzaDto>, Status> visualizzaTutteEsperienze() {

		Response<List<EsperienzaDto>, Status> response = new Response<>();

		try {

			response.setData(eRepository.findAll().stream().map(EsperienzaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienze ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteEsperienze in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}
	
	
	public Response<List<EsperienzaDto>,Status>visualizzaByUtente(long id){
		Response<List<EsperienzaDto>,Status>response = new Response<>();
		try {
			
			List<EsperienzaDto> u = eRepository.findByUtenteId(id).stream().map(EsperienzaMapper::toDto).collect(Collectors.toList());
			response.setData(u);
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienze ritornate con successo.");	
			return response;
			
		}catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaByUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;
		}
	}
	public Response<List<EsperienzaDto>,Status>passaEsperienza(long id,Date dataFine){
		Response<List<EsperienzaDto>,Status>response = new Response<>();
		Esperienza esperienza = eRepository.findById(id).get();
		
		
		try {
			esperienza.setDataFine(dataFine);
			esperienza.setAttuale(false);
			eRepository.save(esperienza);
			response.setStatus(Status.OK);
			response.setDescrizione("Esperienza modificata con successo.");	
			return response;
			
		}catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("passaEsperienza in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;
		}
	}
	
}
