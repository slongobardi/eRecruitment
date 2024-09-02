package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.TitoliStudioDto;
import it.trefin.erecruitment.mapper.TitoliStudioMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.repository.TitoliStudioRepository;

@Service
public class TitoliStudioService {
	private Logger logger = LoggerFactory.getLogger(TitoliStudioService.class);
	@Autowired
	private TitoliStudioRepository tsRepository;
	
	
	public Response<TitoliStudio, Status> inserisciTitoloStudio(TitoliStudio titoliStudio) {

		Response<TitoliStudio, Status> response = new Response<>();

		try {

			tsRepository.save(titoliStudio);
			response.setData(titoliStudio);
			response.setStatus(Status.OK);
			response.setDescrizione("TitoloStudio salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciTitoliStudio in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<TitoliStudioDto, Status> visualizzaTitoloStudio(long id) {

		Response<TitoliStudioDto, Status> response = new Response<>();

		try {

			TitoliStudio titoloStudio = tsRepository.findById(id).get();

			if (titoloStudio != null) {
				TitoliStudioDto dto = TitoliStudioMapper.toDto(titoloStudio);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun titoloStudio trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTitoloStudio in errore: " + e.getMessage());
			logger.warn(e.getMessage());
		}

		return response;
	}

	public Response<TitoliStudioDto, Status> aggiornaTitoloStudio(TitoliStudio titoliStudio, Long id) {

		Response<TitoliStudioDto, Status> response = new Response<>();

		try {

			TitoliStudio ts =  tsRepository.findById(id).get();
	
			ts.setStudi(titoliStudio.getStudi());
			
			
			tsRepository.save(ts);
			response.setData(TitoliStudioMapper.toDto(ts));
			response.setStatus(Status.OK);
			response.setDescrizione("titoloStudio modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaTitoloStudio in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<TitoliStudio, Status> eliminaTitoloStudio(long id) {

		Response<TitoliStudio, Status> response = new Response<>();

		try {

			response.setData((tsRepository.findById(id).get()));
			tsRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("titoloStudio eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaTitoloStudio in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<TitoliStudioDto>, Status> visualizzaTuttiTitoliStudio() {

		Response<List<TitoliStudioDto>, Status> response = new Response<>();

		try {

			response.setData(tsRepository.findAll().stream().map(TitoliStudioMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("titoliStudio ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiTitoliStudio in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}
}
