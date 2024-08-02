package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.mapper.UtenteTitoliStudioMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.UtenteTitoliStudio;
import it.trefin.erecruitment.repository.UtenteTitoliStudioRepository;

@Service
public class UtenteTitoliStudioService {

	@Autowired
	private UtenteTitoliStudioRepository utsRepository;
	
	
	public Response<UtenteTitoliStudio, Status> inserisciUtenteTitoliStudio(UtenteTitoliStudio utenteTitoliStudio) {

		Response<UtenteTitoliStudio, Status> response = new Response<>();

		try {

			utsRepository.save(utenteTitoliStudio);
			response.setData(utenteTitoliStudio);
			response.setStatus(Status.OK);
			response.setDescrizione("utenteTitoliStudio salvato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciUtenteTitoliStudio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteTitoliStudioDto, Status> visualizzaUtenteTitoliStudio(long id) {

		Response<UtenteTitoliStudioDto, Status> response = new Response<>();

		try {

			UtenteTitoliStudio utenteTitoliStudio = utsRepository.findById(id).get();

			if (utenteTitoliStudio != null) {
				UtenteTitoliStudioDto dto = UtenteTitoliStudioMapper.toDto(utenteTitoliStudio);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione(" risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna utenteTitoliStudio trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaUtenteTitoliStudio in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<UtenteTitoliStudioDto, Status> aggiornaUtenteTitoliStudio(UtenteTitoliStudio utenteTitoliStudio, Long id) {

		Response<UtenteTitoliStudioDto, Status> response = new Response<>();

		try {

			UtenteTitoliStudio uts = utsRepository.findById(id).get();
			
			uts.setDataConseguimento(utenteTitoliStudio.getDataConseguimento());
			uts.setDescrizione(utenteTitoliStudio.getDescrizione());
			uts.setTitoliStudio(utenteTitoliStudio.getTitoliStudio());
			uts.setUtente(utenteTitoliStudio.getUtente());
		
			utsRepository.save(uts);
			response.setData(UtenteTitoliStudioMapper.toDto(uts));
			response.setStatus(Status.OK);
			response.setDescrizione("utenteTitoliStudio modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtenteTitoliStudio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteTitoliStudioDto, Status> eliminaUtenteTitoliStudio(long idS) {

		Response<UtenteTitoliStudioDto, Status> response = new Response<>();

		try {
			utsRepository.deleteById(idS);
			response.setStatus(Status.OK);
			response.setDescrizione("eliminata");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtenteTitoliStudio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteTitoliStudioDto>, Status> visualizzaTuttiUtenteTitoliStudio() {

		Response<List<UtenteTitoliStudioDto>, Status> response = new Response<>();

		try {

			response.setData(utsRepository.findAll().stream().map(UtenteTitoliStudioMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("UtenteTitoliStudio ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenteTitoliStudio in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteTitoliStudioDto>, Status> titoliUtente(long id_utente) {
		Response<List<UtenteTitoliStudioDto>, Status> response =new Response<>();
;
		try {
			List<UtenteTitoliStudio> listaTitoli = this.utsRepository.findAllByUtenteId(id_utente);

			response.setData(listaTitoli.stream().map(UtenteTitoliStudioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("TitoliStudio dell'utente ritornati con successo.");
			return response;
			
		}catch(Exception e){
			
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenteTitoliStudio in errore " + e.getMessage());
			return response;
		}
	}
	

}
