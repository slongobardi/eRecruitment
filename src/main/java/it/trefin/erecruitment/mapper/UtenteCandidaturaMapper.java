package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCandidatura;

public class UtenteCandidaturaMapper {
	
	public static UtenteCandidaturaDto toDto(UtenteCandidatura u) {
	    UtenteCandidaturaDto uDto = new UtenteCandidaturaDto();

	    uDto.setDataIscrizione(u.getDataIscrizione());
	    uDto.setId(u.getId());
	    uDto.setNota(u.getNota() != null ? u.getNota() : "");
	    uDto.setStato(u.getStato());

	    uDto.setCandidatura(CandidaturaMapper.toDto( u.getCandidatura())  );
	       uDto.setDisabilitato(u.getDisabilitato());
	

	    uDto.setUtente(
	        u.getUtente() != null ? 
	        u.getUtente().getId() : 
	        -1
	    );

	    return uDto;
	}

	
	public static UtenteCandidatura toEntity(UtenteCandidaturaDto uDto,Candidatura candidatura,Utente utente) {
		UtenteCandidatura u = new UtenteCandidatura();
		u.setDataIscrizione(uDto.getDataIscrizione());
		u.setId(uDto.getId());
		u.setNota(uDto.getNota());
		u.setStato(uDto.getStato());
		u.setCandidatura(candidatura);
		u.setUtente(utente);
		u.setDisabilitato(uDto.getDisabilitato());
		return u;
	}

}
