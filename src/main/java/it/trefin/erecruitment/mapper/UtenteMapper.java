package it.trefin.erecruitment.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Esperienza;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.model.UtenteTitoliStudio;

public class UtenteMapper {

	public static UtenteDto toDto(Utente u) {
	    UtenteDto uDto = new UtenteDto();

	    uDto.setNome(u.getNome());
	    uDto.setCognome(u.getCognome());
	    uDto.setCellulare(u.getCellulare());
	    uDto.setEmail(u.getEmail());
	    uDto.setPassword(u.getPassword());
	    uDto.setCv(u.getCv());
	    uDto.setInCategoriaProtetta(u.isCategoriaProtetta());
	    uDto.setFoto(u.getFoto());
	    uDto.setId(u.getId());
	    uDto.setDescrizione(u.getDescrizione());
	    uDto.setCitta(u.getCitta());
	    uDto.setIndirizzo(u.getIndirizzo());
	    uDto.setVerified(u.isVerified());
	    uDto.setCompleted(u.isCompleted());
	    uDto.setDataNascita(u.getDataNascita());    
	    uDto.setListaSkill(
	    	u.getListaSkill() != null ? 
	    	u.getListaSkill().stream().map(Skill :: getId).collect(Collectors.toSet()): 
	    	new HashSet<>()
	    );
	    
	    uDto.setUtenteTitoliStudio(
	        u.getUtenteTitoliStudio() != null ? 
	        u.getUtenteTitoliStudio().stream().map(UtenteTitoliStudio::getId).collect(Collectors.toList()) : 
	        new ArrayList<>()
	    );
	    uDto.setListaEsperienze(
		        u.getEsperienze() != null ? 
		        u.getEsperienze().stream().map(Esperienza::getId).collect(Collectors.toList()) : 
		        new ArrayList<>()
		    );
	    
	    uDto.setUtentiCandidati(
	        u.getUtentiCandidati() != null ? 
	        u.getUtentiCandidati().stream().map(UtenteCandidatura::getId).collect(Collectors.toList()) : 
	        new ArrayList<>()
	    );
	    
	    uDto.setListaColloquii(
	        u.getListaColloquii() != null ? 
	        u.getListaColloquii().stream().map(Colloquio::getId).collect(Collectors.toSet()) : 
	        new HashSet<>()
	    );
	    
	    uDto.setIdAzienda(
	        u.getAzienda() != null ? 
	        u.getAzienda().getId() : 
	        -1
	    );
	    
	    uDto.setRuolo(u.getRuolo());

	    return uDto;
	}


	public static Utente toEntity(UtenteDto uDto, List<UtenteTitoliStudio> listaUTitoliStudio,
			List<UtenteCandidatura> listaUCandidati, Set<Colloquio> listaColloqui, Azienda a,List<Esperienza>listaEsperienze) {
		Utente u = new Utente();
		u.setNome(uDto.getNome());
		u.setCognome(uDto.getCognome());
		u.setCellulare(uDto.getCellulare());
		u.setEmail(uDto.getEmail());
		u.setPassword(uDto.getPassword());
		u.setCv(uDto.getCv());
		u.setFoto(uDto.getFoto());
		u.setId(uDto.getId());
		u.setCitta(uDto.getCitta());
		u.setIndirizzo(uDto.getIndirizzo());
		u.setDescrizione(uDto.getDescrizione());
		u.setUtenteTitoliStudio(listaUTitoliStudio);
		u.setUtentiCandidati(listaUCandidati);
		u.setListaColloquii(listaColloqui);
		u.setEsperienze(listaEsperienze);
		u.setAzienda(a);

		return u;
	}

}
