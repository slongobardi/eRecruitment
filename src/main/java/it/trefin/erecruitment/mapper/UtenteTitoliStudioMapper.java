package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteTitoliStudio;

public class UtenteTitoliStudioMapper {

	
	public static UtenteTitoliStudioDto toDto(UtenteTitoliStudio u) {
		
		UtenteTitoliStudioDto uDto = new UtenteTitoliStudioDto();
		
		uDto.setData(u.getData());
		uDto.setDescrizione(u.getDescrizione());
		uDto.setId(u.getId());
		uDto.setTitoliStudio(u.getTitoliStudio().getId());
		uDto.setUtente(u.getUtente().getId());
	
		return uDto;
	}
	
	public static UtenteTitoliStudio toEntity(UtenteTitoliStudioDto uDto,TitoliStudio titoliStudio,Utente utente) {
		UtenteTitoliStudio u = new UtenteTitoliStudio();
		u.setData(uDto.getData());
		u.setDescrizione(uDto.getDescrizione());
		u.setId(uDto.getId());
		u.setTitoliStudio(titoliStudio);
		u.setUtente(utente);
		return u;

	}
}
