package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.EsperienzaDto;
import it.trefin.erecruitment.model.Esperienza;

public class EsperienzaMapper {
	
	public static EsperienzaDto toDto(Esperienza e) {
		EsperienzaDto eDto = new EsperienzaDto();
		
		eDto.setAzienda(e.getAzienda());
		eDto.setContratto(e.getContratto());
		eDto.setDescrizione(e.getDescrizione());
		eDto.setId(e.getId());
		eDto.setNome(e.getNome());
		eDto.setUtente(e.getUtente().getId());
		return eDto;
		
		
	}

}
