package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(SkillService.class);
    @Autowired
    private SkillRepository sRepository;

    public Response<Skill, Status> inserisciSkill(Skill skill) {
        Response<Skill, Status> response = new Response<>();

        try {
            sRepository.save(skill);
            response.setData(skill);
            response.setStatus(Status.OK);
            response.setDescrizione("Skill salvata con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("inserisciSkill in errore " + e.getMessage());
			logger.warn(e.getMessage());
        }

        return response;
    }

    public Response<SkillDto, Status> visualizzaSkill(long id) {
        Response<SkillDto, Status> response = new Response<>();

        try {
            Skill skill = sRepository.findById(id).orElse(null);

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
			logger.warn(e.getMessage());
        }

        return response;
    }

    public Response<SkillDto, Status> aggiornaSkill(Skill skill, Long id) {
        Response<SkillDto, Status> response = new Response<>();

        try {
            Skill s = sRepository.findById(id).orElse(null);

            if (s != null) {
                s.setNome(skill.getNome());
                sRepository.save(s);
                response.setData(SkillMapper.toDto(s));
                response.setStatus(Status.OK);
                response.setDescrizione("Skill modificata con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Nessuna skill trovata con l'ID fornito");
            }

        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("aggiornaSkill in errore " + e.getMessage());
			logger.warn(e.getMessage());
        }

        return response;
    }

    public Response<Skill, Status> eliminaSkill(long id) {
        Response<Skill, Status> response = new Response<>();

        try {
            Skill skill = sRepository.findById(id).orElse(null);

            if (skill != null) {
                sRepository.delete(skill);
                response.setData(skill);
                response.setStatus(Status.OK);
                response.setDescrizione("Skill eliminata con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Nessuna skill trovata con l'ID fornito");
            }

        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("eliminaSkill in errore " + e.getMessage());
			logger.warn(e.getMessage());
        }

        return response;
    }

    public Response<List<SkillDto>, Status> visualizzaTutteSkills() {
        Response<List<SkillDto>, Status> response = new Response<>();

        try {
            List<SkillDto> skillDtos = sRepository.findAll().stream()
                    .map(SkillMapper::toDto)
                    .collect(Collectors.toList());
            response.setData(skillDtos);
            response.setStatus(Status.OK);
            response.setDescrizione("Skills ritornate con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("visualizzaTutteSkills in errore " + e.getMessage());
			logger.warn(e.getMessage());
        }

        return response;
    }

    public Response<List<SkillDto>, Status> visualizzaSkillUtente(long id) {
        Response<List<SkillDto>, Status> response = new Response<>();

        try {
            List<SkillDto> skillDtos = sRepository.findAllByUtenteId(id).stream()
                    .map(SkillMapper::toDto)
                    .collect(Collectors.toList());
            response.setData(skillDtos);
            response.setStatus(Status.OK);
            response.setDescrizione("Skills ritornate con successo per l'utente con ID: " + id);
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("visualizzaSkillUtente in errore: " + e.getMessage());
			logger.warn(e.getMessage());
        }

        return response;
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

