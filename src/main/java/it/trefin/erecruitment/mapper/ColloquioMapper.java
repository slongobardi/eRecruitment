package it.trefin.erecruitment.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Utente;

public class ColloquioMapper {
	
	public static ColloquioDto toDto(Colloquio c) {
	    ColloquioDto cDto = new ColloquioDto();

	    cDto.setCognomeEsaminatore(c.getCognomeEsaminatore());
	    cDto.setEsito(c.getEsito());
	    cDto.setId(c.getId());
	    cDto.setNomeEsaminatore(c.getNomeEsaminatore());
	    cDto.setDataColloquio(c.getDataColloquio());
	    cDto.setTipo(c.getTipo());
	    cDto.setCandidatura(c.getCandidatura().getId());
	    cDto.setFeedback(c.getFeedback());
	    cDto.setDescrizione(c.getDescrizione());
	    cDto.setUtente(UtenteMapper.toDto(c.getUtente()));
	    

	    return cDto;
	}

	
	
	public static Colloquio toEntity(ColloquioDto cDto,Utente utente) {
		Colloquio c = new Colloquio();
		c.setCognomeEsaminatore(cDto.getCognomeEsaminatore());
		c.setEsito(cDto.getEsito());
		c.setDescrizione(cDto.getDescrizione());
		c.setId(cDto.getId());
		c.setNomeEsaminatore(cDto.getNomeEsaminatore());
		c.setDataColloquio(cDto.getDataColloquio());
		c.setUtente(utente);
		return  c;
	}

}
