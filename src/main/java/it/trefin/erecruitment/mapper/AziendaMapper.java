package it.trefin.erecruitment.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.AziendaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Utente;

public class AziendaMapper {

    public static AziendaDto toDto(Azienda a) {
        // Creazione del DTO
        AziendaDto aDto = new AziendaDto();

        // Impostazione dei campi di base
        aDto.setDescrizione(a.getDescrizione());
        aDto.setId(a.getId());
        aDto.setIndirizzo(a.getIndirizzo());
        aDto.setLinkAzienda(a.getLinkAzienda());
        aDto.setLuogo(a.getLuogo());
        aDto.setNome(a.getNome());

        // Controllo null per listaCandidature e creazione di una lista vuota se è null
        aDto.setListaCandidature(
            a.getListaCandidature() != null ? 
            a.getListaCandidature().stream().map(Candidatura::getId).collect(Collectors.toList()) : 
            new ArrayList<>()
        );

        // Controllo null per listaUtenti e creazione di una lista vuota se è null
        aDto.setUtenti(
            a.getListaUtenti() != null ? 
            a.getListaUtenti().stream().map(Utente::getId).collect(Collectors.toList()) : 
            new ArrayList<>()
        );

        return aDto;
    }




	public static Azienda toEntity(AziendaDto aDto, List<Utente> utenti,
			List<Candidatura> listaCandidature) {
		Azienda a = new Azienda();

		a.setDescrizione(aDto.getDescrizione());
		a.setId(aDto.getId());
		a.setIndirizzo(aDto.getIndirizzo());
		a.setLinkAzienda(aDto.getLinkAzienda());
		a.setLuogo(aDto.getLuogo());
		a.setNome(aDto.getNome());
		a.setListaUtenti(utenti);
		a.setListaCandidature(listaCandidature);
		return a;
	}
}
