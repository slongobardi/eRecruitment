package it.trefin.erecruitment.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.AziendaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.Utente;

public class AziendaMapper {

	public static AziendaDto toDto(Azienda a) {
		AziendaDto aDto = new AziendaDto();

		aDto.setDescrizione(a.getDescrizione());
		aDto.setId(a.getId());
		aDto.setIndirizzo(a.getIndirizzo());
		aDto.setLinkAzienda(a.getLinkAzienda());
		aDto.setLuogo(a.getLuogo());
		aDto.setNome(a.getNome());
		
		aDto.setSchedaCandidato(a.getSchedaCandidato() != null
				? a.getSchedaCandidato().stream().map(SchedaCandidato::getId).collect(Collectors.toList())
				: new ArrayList<>());

		aDto.setListaCandidature(a.getListaCandidature() != null
				? a.getListaCandidature().stream().map(Candidatura::getId).collect(Collectors.toList())
				: new ArrayList<>());

		aDto.setUtenti(
				a.getListaUtenti() != null ? a.getListaUtenti().stream().map(Utente::getId).collect(Collectors.toList())
						: new ArrayList<>());

		aDto.setPreferenze(a.getPreferenza() != null
				? a.getPreferenza().stream().map(Preferenza::getId).collect(Collectors.toList())
				: new ArrayList<Long>());

		return aDto;
	}

	public static Azienda toEntity(AziendaDto aDto, List<Utente> utenti, List<Candidatura> listaCandidature) {
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
