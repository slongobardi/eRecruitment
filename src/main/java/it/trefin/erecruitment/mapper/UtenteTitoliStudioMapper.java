package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteTitoliStudio;

public class UtenteTitoliStudioMapper {

	public static UtenteTitoliStudioDto toDto(UtenteTitoliStudio u) {
		UtenteTitoliStudioDto uDto = new UtenteTitoliStudioDto();

		uDto.setAnnoInizio(u.getAnnoInizio());
		uDto.setDataConseguimento(u.getDataConseguimento());
		uDto.setNomeTitoliStudio(u.getTitoliStudio().getStudi().getTitolo());
		uDto.setDescrizione(u.getDescrizione());
		uDto.setVoto(u.getVoto());
		uDto.setId(u.getId());
		uDto.setLode(u.getLode());
		uDto.setTitoliStudio(u.getTitoliStudio() != null ? u.getTitoliStudio().getId() : -1);
		uDto.setUtente(u.getUtente() != null ? u.getUtente().getId() : -1);
		uDto.setCompleted(u.isCompleted());
		return uDto;
	}

	public static UtenteTitoliStudio toEntity(UtenteTitoliStudioDto uDto, TitoliStudio titoliStudio, Utente utente) {
		UtenteTitoliStudio u = new UtenteTitoliStudio();
		u.setAnnoInizio(uDto.getAnnoInizio());
		u.setDataConseguimento(uDto.getDataConseguimento());
		u.setVoto(uDto.getVoto());
		u.setDescrizione(uDto.getDescrizione());
		u.setId(uDto.getId());
		u.setTitoliStudio(titoliStudio);
		u.setUtente(utente);
		u.setLode(uDto.getLode());
		return u;

	}
}
