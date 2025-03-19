package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.UtenteCorsoDto;
import it.trefin.erecruitment.model.UtenteCorso;

public class UtenteCorsoMapper {
	public static UtenteCorsoDto toDTO(UtenteCorso utenteCorso) {
		UtenteCorsoDto ucDto = new UtenteCorsoDto();
		ucDto.setId(utenteCorso.getId());
		ucDto.setUtenteId(utenteCorso.getUtente().getId());
		ucDto.setCorsoId(utenteCorso.getCorso().getId());
        return ucDto;
	}
	public static UtenteCorso toEntity(UtenteCorsoDto dto) {
        UtenteCorso utenteCorso = new UtenteCorso();
        utenteCorso.setId(dto.getId());
        return utenteCorso;
    }
}
