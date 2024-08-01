package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.PreferenzaDto;
import it.trefin.erecruitment.mapper.PreferenzaMapper;
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.repository.PreferenzaRepository;

@Service
public class PreferenzaService {
	@Autowired
	private PreferenzaRepository repository;

	public Response<List<PreferenzaDto>, Status> findAllByUtente(long id) {
		Response<List<PreferenzaDto>, Status> response = new Response<>();

		try {
			List<Preferenza> byUtente = repository.findAllByUtente(id);
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
		}

		return response;
	}

	public Response<PreferenzaDto, Status> add(Preferenza p) {
		Response<PreferenzaDto, Status> response = new Response<>();

		try {
			repository.save(p);
			response.setData(PreferenzaMapper.toDto(p));
			response.setDescrizione("preferenze trovate");
			response.setStatus(Status.OK);

		} catch (Exception e) {
			response.setDescrizione(e.getMessage());
			response.setStatus(Status.KO);
		}

		return response;
	}
}
