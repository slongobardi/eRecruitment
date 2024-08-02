package it.trefin.erecruitment.mapper;


import it.trefin.erecruitment.dto.PreferenzaDto;
import it.trefin.erecruitment.model.Preferenza;

public class PreferenzaMapper {
	public static PreferenzaDto toDto(Preferenza p) {
		PreferenzaDto dto = new PreferenzaDto();
		dto.setId(p.getId());
		dto.setNomeAzienda(p.getAzienda().getNome());
		dto.setAzienda(p.getAzienda().getId());
		dto.setUtente(p.getUtente().getId());
		
		return dto;
	}
}
