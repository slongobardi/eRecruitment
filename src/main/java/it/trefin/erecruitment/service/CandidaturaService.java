package it.trefin.erecruitment.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.mapper.CandidaturaMapper;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.mapper.UtenteMapper;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.repository.AziendaRepository;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.SchedaCandidatoRepository;
import it.trefin.erecruitment.repository.SkillRepository;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class CandidaturaService {
	private Logger logger = LoggerFactory.getLogger(CandidaturaService.class);
	@Autowired
	private CandidaturaRepository cRepository;
	@Autowired
	private SkillRepository sRepository;
	@Autowired
	private AziendaRepository aRepository;
	@Autowired
	private UtenteCandidaturaRepository uRepository;

	public Response<CandidaturaDto, Status> inserisciCandidatura(CandidaturaDto candidaturaDto) {
		Response<CandidaturaDto, Status> response = new Response<>();
		Azienda azienda = this.aRepository.getReferenceById(candidaturaDto.getAzienda());
		Set<Skill> listaSkill = new HashSet<>();

		try {
			for (long skill : candidaturaDto.getListaSkill()) {
				listaSkill.add(sRepository.getReferenceById(skill));
			}
			
			Candidatura candidatura = CandidaturaMapper.toEntity(candidaturaDto, azienda, null, listaSkill, null);
			cRepository.save(candidatura);
			response.setData(CandidaturaMapper.toDto(candidatura));
			response.setStatus(Status.OK);
			response.setDescrizione("candidatura salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciCandidatura in errore " + e.getMessage());
			logger.warn(e.getMessage());
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
			logger.warn(e.getMessage());
		}

		return response;
	}

	public Response<CandidaturaDto, Status> aggiornaCandidatura(CandidaturaDto candidaturaDTO) {

		Response<CandidaturaDto, Status> response = new Response<>();
		Candidatura candidatura = this.cRepository.getReferenceById(candidaturaDTO.getId());
		Set<Skill> listaSkill = new HashSet<>();
		List<UtenteCandidatura> listaUtenteCandidatura = new ArrayList<>();

		for (long skill : candidaturaDTO.getListaSkill()) {
			listaSkill.add(this.sRepository.getReferenceById(skill));
		}
		for (long utenteCandidatura : candidaturaDTO.getUtenteCandidature()) {
			listaUtenteCandidatura.add(this.uRepository.getReferenceById(utenteCandidatura));
		}
		try {

			candidatura = CandidaturaMapper.toEntity(candidaturaDTO, candidatura.getAzienda(), listaUtenteCandidatura,
					listaSkill, candidatura.getListaTitoliStudio());
			System.out.println(candidatura.getNumeroCandidati());
			this.cRepository.save(candidatura);
			response.setData(CandidaturaMapper.toDto(candidatura));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidatura modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaCandidatura in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<CandidaturaDto, Status> eliminaCandidatura(long id) {

		Response<CandidaturaDto, Status> response = new Response<>();

		try {
			Candidatura c = cRepository.findById(id).get();
			response.setData(CandidaturaMapper.toDto(c));
			cRepository.delete(c);
			response.setStatus(Status.OK);
			response.setDescrizione("Candidatura eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaCandidatura in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<CandidaturaDto>, Status> visualizzaTutteCandidature() {

		Response<List<CandidaturaDto>, Status> response = new Response<>();

		try {

			response.setData(cRepository.findAll().stream().map(CandidaturaMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteCandidature in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;

		}

	}

	public Response<List<CandidaturaDto>, Status> visualizzaCandidatureAziendali(long id_azienda) {
		Response<List<CandidaturaDto>, Status> response = new Response<>();

		try {
			response.setData(cRepository.findAllByAziendaId(id_azienda).stream().map(CandidaturaMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature aziendali ritornate con successo");
			return response;

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatureAziendali in errore " + e.getMessage());
			logger.warn(e.getMessage());
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
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaCandidatureAziendali in errore " + e.getMessage());
			logger.warn(e.getMessage());
			return response;
		}
	}
	
	public Response<Object[], Status> findByUtente(long idU,long idA) {
		Response<Object[], Status> response = new Response<>();

		try {
			response.setData(cRepository.findByUtente(idU,idA));
			response.setDescrizione("query custom feedback ok");
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDescrizione("errore query custom feedback " + e.getMessage());
			response.setStatus(Status.KO);
		}

		return response;
	}

	public Response<CandidaturaDto, Status> updateDescrizione(long id_candidatura) {
		Response<CandidaturaDto, Status> response = new Response<>();
		
		try {
			Candidatura candDisable = cRepository.getReferenceById(id_candidatura);
			candDisable.setDisabilitato(!candDisable.getDisabilitato());
			candDisable.setNumeroCandidati(0);
			cRepository.save(candDisable);
			if(candDisable.getDisabilitato()== true) {
				ArrayList<UtenteCandidatura> listaCandidaturaUtenti = uRepository.getFindByCandidaturaId(id_candidatura);
				for (UtenteCandidatura utenteCandidatura : listaCandidaturaUtenti) {
					utenteCandidatura.setDisabilitato(true);
					uRepository.save(utenteCandidatura);
				}
			}
			response.setData(CandidaturaMapper.toDto(candDisable));
			response.setDescrizione("Disabilitati ok");
			response.setStatus(Status.OK);
		}catch (Exception e) {
			response.setDescrizione("Disabilitati errore " + e.getMessage());
			response.setStatus(Status.KO);
		}
		return response;
	}

	public Response<List<CandidaturaDto>, Status> getEventi(long id_azienda) {
		Response<List<CandidaturaDto>, Status> response = new Response<>();

		try {
			List<Candidatura> candidature = cRepository.findByIsEventoTrueAndAziendaId(id_azienda);

			List<CandidaturaDto> dtoList = candidature.stream().map(c -> {
				CandidaturaDto dto = CandidaturaMapper.toDto(c);

				int numeroCandidati = uRepository.countByCandidaturaId(c.getId());
				dto.setNumeroCandidati(numeroCandidati);

				return dto;
			}).collect(Collectors.toList());

			response.setData(dtoList);
			response.setStatus(Status.OK);
			response.setDescrizione("Candidature ritornate con successo.");
			return response;

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("Errore durante il recupero delle candidature evento: " + e.getMessage());
			logger.warn(e.getMessage());
			return response;
		}
	}

	public Response<CandidaturaDto, Status> aggiornaLogo(Long id, MultipartFile foto) {
		Response<CandidaturaDto, Status> response = new Response<>();
		try {
			Candidatura c = cRepository.findById(id).orElse(null);
			if (c != null && !foto.isEmpty()) {
				c.setLogoEvento(foto.getBytes());
				cRepository.save(c);
				CandidaturaDto dto = CandidaturaMapper.toDto(c);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Foto Utente salvata con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Foto è null o Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaFoto in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

}