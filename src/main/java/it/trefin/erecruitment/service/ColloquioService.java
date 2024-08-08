package it.trefin.erecruitment.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.mapper.ColloquioMapper;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Feedback;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.ColloquioRepository;

@Service
public class ColloquioService {

	@Autowired
	private ColloquioRepository cRepository;

	@Autowired
	private CandidaturaRepository candidaturaRepository;

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

			Colloquio c = cRepository.findById(id).get();

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

			response.setData(cRepository.findAll().stream().map(ColloquioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Colloquii ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiColloqui in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<ColloquioDto>, Status> colloquiCandidatura(long idCandidatura, long idUtente) {

		Response<List<ColloquioDto>, Status> response = new Response<>();

		try {

			List<Colloquio> colloquiList = cRepository.findByCandidaturaId(idCandidatura);

			List<ColloquioDto> colloquiDtoList = colloquiList.stream().map(ColloquioMapper::toDto)
					.collect(Collectors.toList());

			colloquiDtoList.stream().filter(c -> c.getListaUtenti().contains(idUtente)).collect(Collectors.toList());

			response.setData(colloquiDtoList);
			response.setStatus(Status.OK);
			response.setDescrizione("Colloqui ritornati con successo.");
			return response;

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiColloqui in errore " + e.getMessage());
			return response;
		}
	}

	public Response<ColloquioDto, Status> updateFeedback(Feedback f, long id) {
		Response<ColloquioDto, Status> response = new Response<>();

		try {
			Colloquio c = cRepository.findById(id).get();
			c.setFeedback(f);
			cRepository.save(c);
			response.setData(ColloquioMapper.toDto(c));
			response.setStatus(Status.OK);
			response.setDescrizione("Feedback aggiornato");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("update feedback in errore " + e.getMessage());
			return response;

		}
	}

	public Response<ColloquioDto, Status> updateDescrizione(String descrizione, long id) {
		Response<ColloquioDto, Status> response = new Response<>();

		try {
			Colloquio c = cRepository.findById(id).get();
			c.setDescrizione(descrizione);
			cRepository.save(c);
			response.setData(ColloquioMapper.toDto(c));
			response.setStatus(Status.OK);
			response.setDescrizione("Descrizione aggiornata");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("update descrizione in errore " + e.getMessage());
			return response;

		}
	}

	public Response<List<ColloquioDto>, Status> filterByDate(String start, String end) {
		Response<List<ColloquioDto>, Status> response = new Response<>();

		try {
			ArrayList<Colloquio> filtered = cRepository.findByUltimoColloquioBetween(Date.valueOf(start),
					Date.valueOf(end));
			if (filtered.size() == 0) {
				response.setData(new ArrayList<>());
				response.setStatus(Status.OK);
				response.setDescrizione("filter by date success");
			} else {
				response.setData(filtered.stream().map(ColloquioMapper::toDto).collect(Collectors.toList()));
				response.setStatus(Status.OK);
				response.setDescrizione("filter by date success");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("filter by date in errore " + e.getMessage());
		}

		return response;
	}

	public Response<Object[], Status> totalFeedback(int id, Date startDate, Date endDate) {
		Response<Object[], Status> response = new Response<>();

		try {
			response.setData(cRepository.totalFeedback(id, startDate, endDate));
			response.setDescrizione("query custom feedback ok");
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDescrizione("errore query custom feedback " + e.getMessage());
			response.setStatus(Status.KO);
		}

		return response;
	}
	
	public Response<Set<Object>, Status> report(long id, Date startDate, Date endDate) {
		Response<Set<Object>, Status> response = new Response<>();

		try {
			response.setData(cRepository.reportCandidati(id, startDate, endDate));
			response.setDescrizione("query custom feedback ok");
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDescrizione("errore query custom feedback " + e.getMessage());
			response.setStatus(Status.KO);
		}

		return response;
	}
	
	public Response<List<ColloquioDto>,Status> findAllColloquiUtenteByCandidatura(long idC,long idU){
		Response<List<ColloquioDto>,Status> response  = new Response<>();
		
		try {
			response.setData(cRepository.findAllColloquiUtenteByCandidatura(idC,idU).stream().map(ColloquioMapper::toDto).collect(Collectors.toList()));
			response.setDescrizione("query custom feedback ok");
			response.setStatus(Status.OK);
		}catch(Exception e){
			response.setDescrizione("errore query custom findAllColloquiUtenteByCandidatura " + e.getMessage());
			response.setStatus(Status.KO);
		}
		
		return response;
	}

}
