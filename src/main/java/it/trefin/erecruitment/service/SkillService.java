package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository sRepository;
	
	
	public Response<Skill, Status> inserisciSkill(Skill skill) {

		Response<Skill, Status> response = new Response<>();

		try {

			sRepository.save(skill);
			response.setData(skill);
			response.setStatus(Status.OK);
			response.setDescrizione("Skill salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciSkill in errore " + e.getMessage());
			return response;

		}

	}

	public Response<SkillDto, Status> visualizzaSkill(long id) {

		Response<SkillDto, Status> response = new Response<>();

		try {

			Skill skill = sRepository.findById(id).get();

			if (skill != null) {
				SkillDto dto = SkillMapper.toDto(skill);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna skill trovata con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaSkill in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<SkillDto, Status> aggiornaSkill(Skill skill, Long id) {

		Response<SkillDto, Status> response = new Response<>();

		try {

			Skill s =  sRepository.findById(id).get();
	
			s.setNome(skill.getNome());
			
			sRepository.save(s);
			response.setData(SkillMapper.toDto(s));
			response.setStatus(Status.OK);
			response.setDescrizione("Skill modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaSkill in errore " + e.getMessage());
			return response;

		}

	}

	public Response<Skill, Status> eliminaSkill(long id) {

		Response<Skill, Status> response = new Response<>();

		try {

			response.setData((sRepository.findById(id).get()));
			sRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("Skill eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaSkill in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<SkillDto>, Status> visualizzaTutteSkills() {

		Response<List<SkillDto>, Status> response = new Response<>();

		try {

			response.setData(sRepository.findAll().stream().map(SkillMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Skills ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteSkills in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<SkillDto>, Status> visualizzaSkillsTipologia(long id) {
		Response<List<SkillDto>,Status> listaSkills=new Response<>();
		
		try {
			listaSkills.setData(this.sRepository.findAllByTipologiaId(id).stream().map(SkillMapper::toDto).collect(Collectors.toList()));
			listaSkills.setStatus(Status.OK);
			listaSkills.setDescrizione("Skill recuperate correttamente");
			return listaSkills;
		}catch(Exception e) {
			listaSkills.setStatus(Status.SYSTEM_ERROR);
			listaSkills.setDescrizione("Le skill non sono state trovate per via di un errore "+e.getMessage());
			return listaSkills;
		}

	}

//	public Response<List<SkillDto>, Status> skillCandidatura(Long id_candidatura) {
//		Response<List<SkillDto>, Status> response = new Response<>();
//		
//		try {
//			response.setData(sRepository.findAllByCandidaturaId(id_candidatura).stream().map(SkillMapper::toDto).collect(Collectors.toList()));
//			response.setStatus(Status.OK);
//			response.setDescrizione("Skills ritornate con successo.");
//			return response;
//			
//		}catch(Exception e) {
//			response.setStatus(Status.SYSTEM_ERROR);
//			response.setDescrizione("skillCandidatura in errore " + e.getMessage());
//			return response;
//		}	
//	}
}
