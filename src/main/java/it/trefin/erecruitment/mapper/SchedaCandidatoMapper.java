package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.SchedaCandidatoDto;
import it.trefin.erecruitment.model.SchedaCandidato;

public class SchedaCandidatoMapper {
	
	public static SchedaCandidatoDto toDto(SchedaCandidato sc) {
		SchedaCandidatoDto scDto = new SchedaCandidatoDto();
		scDto.setId(sc.getId());
		scDto.setNota(sc.getNota());
		scDto.setIngaggiato(sc.getIngaggiato());
		scDto.setPerso(sc.getPerso());
		scDto.setPicking(sc.getPicking());
		
		return scDto;
	}
	
	
	public static SchedaCandidato toEntity(SchedaCandidatoDto scDto) {
		SchedaCandidato sc = new SchedaCandidato();;
		sc.setNota(scDto.getNota());
		sc.setIngaggiato(scDto.getIngaggiato());
		sc.setPerso(scDto.getPerso());
		sc.setPicking(scDto.getPicking());
		sc.setNota(scDto.getNota());
		sc.setIngaggiato(scDto.getIngaggiato());
		sc.setPerso(scDto.getPerso());
		sc.setPicking(scDto.getPicking());
		
		return sc;
	}

}
