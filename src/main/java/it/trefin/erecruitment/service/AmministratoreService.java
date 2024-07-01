package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.AmministratoreDto;
import it.trefin.erecruitment.mapper.AmministratoreMapper;
import it.trefin.erecruitment.model.Amministratore;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.repository.AmministratoreRepository;

@Service
public class AmministratoreService {

	@Autowired
	private AmministratoreRepository aRepository;

	public Response<Amministratore, Status> save(Amministratore amministratore) {

		Response<Amministratore, Status> response = new Response<>();

		try {
			aRepository.save(amministratore);
			response.setData(amministratore);
			response.setStatus(Status.OK);
			response.setDescrizione("amministratore salvato con successo.");
			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("saveAmministratore in errore " + e.getMessage());
			return response;
		}
	}

	public Response<AmministratoreDto, Status> visualizzaAmministratore(long id) {

		Response<AmministratoreDto, Status> response = new Response<>();

		try {

			Amministratore amministratore = aRepository.findById(id).get();

			if (amministratore != null) {
				AmministratoreDto dto = AmministratoreMapper.toDto(amministratore);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("visualizzaAmministratore risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun amministratore trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaAmministratore in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<List<AmministratoreDto>, Status> visualizzaTuttiAmministratori() {

		Response<List<AmministratoreDto>, Status> response = new Response<>();

		try {

			response.setData(aRepository.findAll().stream().map(AmministratoreMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Amministratori ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiAmministratori in errore " + e.getMessage());
			return response;

		}

	}
}
