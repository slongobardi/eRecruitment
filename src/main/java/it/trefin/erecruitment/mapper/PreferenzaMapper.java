package it.trefin.erecruitment.mapper;

import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.PreferenzaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.Utente;

public class PreferenzaMapper {
	public static PreferenzaDto toDto(Preferenza p) {
		PreferenzaDto dto = new PreferenzaDto();
		
		dto.setAzienda(p.getAzienda().getId());
		dto.setUtente(p.getUtente().getId());
		
		return dto;
	}
}
