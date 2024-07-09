package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.mapper.UtenteMapper;
import it.trefin.erecruitment.mapper.UtenteTitoliStudioMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteTitoliStudio;
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
	
	public Response<List<SkillDto>, Status>skillUtente(long id) {
		
		Response<List<SkillDto>, Status> response = new Response<>();

		try {
			List<Skill> listaSkillUtente=new ArrayList<>(this.uRepository.getReferenceById(id).getListaSkill());
			response.setData(listaSkillUtente.stream().map(SkillMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("SkillUtente con successo.");

			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("SkillUtente in errore " + e.getMessage());
			return response;

		}
	}
	
	public Response<List<UtenteTitoliStudioDto>, Status>titoliUtente(long id) {
		
		Response<List<UtenteTitoliStudioDto>, Status> response = new Response<>();

		try {
			List<UtenteTitoliStudio> listaSkillUtente=new ArrayList<>(this.uRepository.getReferenceById(id).getUtenteTitoliStudio());
			response.setData(listaSkillUtente.stream().map(UtenteTitoliStudioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("titoliUtente con successo.");

			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("titoliUtente in errore " + e.getMessage());
			return response;

		}
	}
	
	public Response<List<UtenteDto>,Status> getAllNotUser(){
		Response<List<UtenteDto>, Status> response = new Response<>();
		
		try {
			List<Utente> responseQuery = uRepository.findAllNotUser();
			response.setData(responseQuery.stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("ok");
			
			return response;
		}catch(Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("allNotUser in errore " + e.getMessage());
			return response;
		}
	}
	
	
}
