package it.trefin.erecruitment.service;

import java.util.*;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.mapper.CandidaturaMapper;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.SkillRepository;

@Service
public class CandidaturaService {

	@Autowired
	private CandidaturaRepository cRepository;
	@Autowired
	private SkillRepository sRepository;
	
	public Response<Candidatura, Status> inserisciCandidatura(Candidatura candidatura) {

		Response<Candidatura, Status> response = new Response<>();

		try {

			cRepository.save(candidatura);
			response.setData(candidatura);
			response.setStatus(Status.OK);
			response.setDescrizione("candidatura salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<CandidaturaDto, Status> visualizzaCandidatura(long id) {

		Response<CandidaturaDto, Status> response = new Response<>();

		try {

			Candidatura candidatura = cRepository.findById(id).get();

			if (candidatura != null) {
				CandidaturaDto dto = CandidaturaMapper.toDto(candidatura);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione(" risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna candidatura trovata con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatura in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<CandidaturaDto, Status> aggiornaCandidatura(CandidaturaDto candidaturaDTO) {

		Response<CandidaturaDto, Status> response = new Response<>();
		Candidatura candidatura = this.cRepository.getReferenceById(candidaturaDTO.getId());
		Set<Skill> listaSkill = new HashSet<>();
		for (long skill : candidaturaDTO.getListaSkill()) {
			listaSkill.add(this.sRepository.getReferenceById(skill));
		}
		try {

			
			candidatura=CandidaturaMapper.toEntity(candidaturaDTO,candidatura.getAzienda(),candidatura.getUtenteCandidature(),listaSkill,candidatura.getListaTitoliStudio());
			this.cRepository.save(candidatura);
			response.setData(CandidaturaMapper.toDto(candidatura));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidatura modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<Candidatura, Status> eliminaCandidatura(long id) {

		Response<Candidatura, Status> response = new Response<>();

		try {

			response.setData(cRepository.findById(id).get());
			cRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("Candidatura eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaCandidatura in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<CandidaturaDto>, Status> visualizzaTutteCandidature() {

		Response<List<CandidaturaDto>, Status> response = new Response<>();

		try {

			response.setData(cRepository.findAll().stream().map(CandidaturaMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteCandidature in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<CandidaturaDto>, Status> visualizzaCandidatureAziendali(long id_azienda) {
		Response<List<CandidaturaDto>, Status> response = new Response<>();
		
		try {
			response.setData(cRepository.findAllByAziendaId(id_azienda).stream().map(CandidaturaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature aziendali ritornate con successo");
			return response;
			
		}catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatureAziendali in errore " + e.getMessage());
			return response;
		}
		
	}

	public Response<List<SkillDto>, Status> skillDellaCandidatura(long id_candidatura) {
		
		Response<List<SkillDto>, Status> response = new Response<>();
		try {
			List<Skill> listaSkill = new ArrayList<>(cRepository.getReferenceById(id_candidatura).getListaSkill());
			response.setData(listaSkill.stream().map(SkillMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature aziendali ritornate con successo");
			return response;
		}catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatureAziendali in errore " + e.getMessage());
			return response;
		}
	}
}
