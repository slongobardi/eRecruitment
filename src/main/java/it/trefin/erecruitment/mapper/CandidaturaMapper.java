package it.trefin.erecruitment.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.UtenteCandidatura;

public class CandidaturaMapper {

	public static CandidaturaDto toDto(Candidatura c) {
		CandidaturaDto cDto = new CandidaturaDto();

		cDto.setDescrizione(c.getDescrizione());
		cDto.setId(c.getId());
		cDto.setNome(c.getNome());
		cDto.setNumeroCandidati(c.getNumeroCandidati());
		cDto.setPubblicazione(c.getPubblicazione());
		cDto.setAzienda(c.getAzienda().getId());
		cDto.setListaSkill(c.getListaSkill().stream().map(Skill::getId).collect(Collectors.toSet()));
		cDto.setUtenteCandidature(c.getUtenteCandidature().stream().map(UtenteCandidatura::getId).collect(Collectors.toList()));
		return cDto;

	}
	
	
	public static Candidatura toEntity(CandidaturaDto cDto,Azienda azienda,List<UtenteCandidatura> utenteCandidature,Set<Skill>listaSkill) {
		Candidatura c = new Candidatura();
		c.setDescrizione(cDto.getDescrizione());
		c.setId(cDto.getId());
		c.setNome(cDto.getNome());
		c.setNumeroCandidati(c.getNumeroCandidati());
		c.setPubblicazione(cDto.getPubblicazione());
		c.setAzienda(azienda);
		c.setUtenteCandidature(utenteCandidature);
		c.setListaSkill(listaSkill);
		return c;
	}
}
