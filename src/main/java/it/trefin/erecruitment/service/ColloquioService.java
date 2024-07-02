package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.mapper.ColloquioMapper;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.repository.ColloquioRepository;

@Service
public class ColloquioService {

	@Autowired
	private ColloquioRepository cRepository;
	
	
	public Response<Colloquio, Status> inserisciColloquio(Colloquio colloquio) {

		Response<Colloquio, Status> response = new Response<>();

		try {

			cRepository.save(colloquio);
			response.setData(colloquio);
			response.setStatus(Status.OK);
			response.setDescrizione("Colloquio salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciColloquio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<ColloquioDto, Status> visualizzaColloquio(long id) {

		Response<ColloquioDto, Status> response = new Response<>();

		try {

			Colloquio colloquio = cRepository.findById(id).get();

			if (colloquio != null) {
				ColloquioDto dto = ColloquioMapper.toDto(colloquio);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun colloquio trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaColloquio in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<ColloquioDto, Status> aggiornaColloquio(Colloquio colloquio, Long id) {

		Response<ColloquioDto, Status> response = new Response<>();

		try {

			Colloquio c =  cRepository.findById(id).get();
	
			c.setCognomeEsaminatore(colloquio.getCognomeEsaminatore());
			c.setDescrizione(colloquio.getDescrizione());
			c.setEsito(colloquio.getEsito());
			c.setNomeEsaminatore(colloquio.getNomeEsaminatore());
			c.setProssimoColloquio(colloquio.getProssimoColloquio());
			c.setUltimoColloquio(colloquio.getUltimoColloquio());
			
			cRepository.save(c);
			response.setData(ColloquioMapper.toDto(c));
			response.setStatus(Status.OK);
			response.setDescrizione("colloquio modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaColloquio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<Colloquio, Status> eliminaColloquio(long id) {

		Response<Colloquio, Status> response = new Response<>();

		try {

			response.setData((cRepository.findById(id).get()));
			cRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("colloquio eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaColloquio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<ColloquioDto>, Status> visualizzaTuttiColloqui() {

		Response<List<ColloquioDto>, Status> response = new Response<>();

		try {

			response.setData(cRepository.findAll().stream().map(ColloquioMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Colloquii ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiColloqui in errore " + e.getMessage());
			return response;

		}

	}
}
