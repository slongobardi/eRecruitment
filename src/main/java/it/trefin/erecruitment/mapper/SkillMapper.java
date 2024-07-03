package it.trefin.erecruitment.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Tipologia;

public class SkillMapper {
	
	public static SkillDto toDto(Skill s) {
	    SkillDto sDto = new SkillDto();

	    sDto.setId(s.getId());
	    sDto.setNome(s.getNome());

	    sDto.setTipologia(
	        s.getTipologia() != null ? 
	        s.getTipologia().getId() : 
	        -1
	    );

	    sDto.setListaCandidature(
	        s.getListaCandidature() != null ? 
	        s.getListaCandidature().stream().map(Candidatura::getId).collect(Collectors.toSet()) : 
	        new HashSet<>()
	    );

	    return sDto;
	}

	
	public static Skill toEntity(SkillDto sDto,Tipologia tipologia,Set<Candidatura>listaCandidature) {
		Skill s = new Skill();
		
		s.setId(sDto.getId());
		s.setNome(sDto.getNome());
		s.setTipologia(tipologia);
		s.setListaCandidature(listaCandidature);
		
		return s;
	
		
	}

}
