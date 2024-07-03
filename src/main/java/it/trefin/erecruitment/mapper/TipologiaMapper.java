package it.trefin.erecruitment.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.TipologiaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Tipologia;

public class TipologiaMapper {
	
	public static TipologiaDto toDto(Tipologia t) {
	    TipologiaDto tDto = new TipologiaDto();

	    tDto.setId(t.getId());
	    tDto.setNome(t.getNome());

	    tDto.setListaAziende(
	        t.getListaAziende() != null ? 
	        t.getListaAziende().stream().map(Azienda::getId).collect(Collectors.toList()) : 
	        new ArrayList<>()
	    );

	    tDto.setListaSkill(
	        t.getListaSkill() != null ? 
	        t.getListaSkill().stream().map(Skill::getId).collect(Collectors.toList()) : 
	        new ArrayList<>()
	    );

	    return tDto;
	}


	
	public static Tipologia toEntity(TipologiaDto tDto,List<Skill>listaSkill,List<Azienda>listaAziende) {
		Tipologia t = new Tipologia();
		
		t.setId(tDto.getId());
		t.setNome(tDto.getNome());
		t.setListaSkill(listaSkill);
		t.setListaAziende(listaAziende);
		
		return t;
	}
}
