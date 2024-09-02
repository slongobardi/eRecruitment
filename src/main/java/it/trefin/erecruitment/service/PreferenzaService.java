package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.PreferenzaDto;
import it.trefin.erecruitment.mapper.PreferenzaMapper;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.AziendaRepository;
import it.trefin.erecruitment.repository.PreferenzaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class PreferenzaService {
	private Logger logger = LoggerFactory.getLogger(PreferenzaService.class);
	@Autowired
	private PreferenzaRepository repository;
	@Autowired
	private UtenteRepository urepository;
	@Autowired
	private AziendaRepository arepository;

	public Response<List<PreferenzaDto>, Status> findAllByUtente(long id) {
		Response<List<PreferenzaDto>, Status> response = new Response<>();

		try {
			List<Preferenza> byUtente = repository.findAllByUtenteId(id);
			if (byUtente.size() > 0) {
				response.setData(byUtente.stream().map(PreferenzaMapper::toDto).collect(Collectors.toList()));
				response.setDescrizione("preferenze trovate");
				response.setStatus(Status.OK);
			} else {
				response.setData(new ArrayList<PreferenzaDto>());
				response.setDescrizione("preferenze trovate");
				response.setStatus(Status.OK);
			}

		} catch (Exception e) {
			response.setDescrizione(e.getMessage());
			response.setStatus(Status.KO);
			logger.warn(e.getMessage());
		}

		return response;
	}

	public Response<PreferenzaDto, Status> add(long idAzienda, long idUtente) {
		Response<PreferenzaDto, Status> response = new Response<>();

		try {
			Utente u = urepository.findById(idUtente).get();
			Azienda a = arepository.findById(idAzienda).get();

			Preferenza p = new Preferenza();
			p.setAzienda(a);
			p.setUtente(u);
			repository.save(p);
			response.setData(PreferenzaMapper.toDto(p));
			response.setDescrizione("preferenze trovate");
			response.setStatus(Status.OK);

		} catch (Exception e) {
			response.setDescrizione(e.getMessage());
			response.setStatus(Status.KO);
			logger.warn(e.getMessage());
		}

		return response;
	}
	
	public Response<String,Status>deleteById(long id){
		Response<String, Status> response = new Response<>();
		
		try {
			repository.deleteById(id);
			response.setData("Cancellato");
			response.setDescrizione("preferenza calcellata con successo");
			response.setStatus(Status.OK);
			return response;
		}catch(Exception e){
			response.setDescrizione(e.getMessage());
			response.setStatus(Status.KO);
			logger.warn(e.getMessage());
			return response;
		}

	}
}
