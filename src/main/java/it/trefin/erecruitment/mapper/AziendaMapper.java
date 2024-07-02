package it.trefin.erecruitment.mapper;

import java.util.List;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.AziendaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Tipologia;
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
		aDto.setTipologia(a.getTipologia().getId());
		aDto.setListaCandidature(a.getListaCandidature().stream().map(Candidatura::getId).collect(Collectors.toList()));
		aDto.setUtenti(a.getListaUtenti().stream().map(Utente::getId).collect(Collectors.toList()));
		return aDto;
	}

	public static Azienda toEntity(AziendaDto aDto, Tipologia tipologia, List<Utente> utenti,
			List<Candidatura> listaCandidature) {
		Azienda a = new Azienda();

		a.setDescrizione(aDto.getDescrizione());
		a.setId(aDto.getId());
		a.setIndirizzo(aDto.getIndirizzo());
		a.setLinkAzienda(aDto.getLinkAzienda());
		a.setLuogo(aDto.getLuogo());
		a.setNome(aDto.getNome());
		a.setTipologia(tipologia);
		a.setListaUtenti(utenti);
		a.setListaCandidature(listaCandidature);
		return a;
	}
}
