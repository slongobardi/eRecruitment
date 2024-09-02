package it.trefin.erecruitment.service;

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
import it.trefin.erecruitment.repository.EsperienzaRepository;

@Service
public class EsperienzaService {
	private Logger logger = LoggerFactory.getLogger(EsperienzaService.class);
	@Autowired
	private EsperienzaRepository eRepository;
	
	
	public Response<Esperienza, Status> inserisciEsperienza(Esperienza esperienza) {

		Response<Esperienza, Status> response = new Response<>();

		try {

			eRepository.save(esperienza);
			response.setData(esperienza);
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
}
