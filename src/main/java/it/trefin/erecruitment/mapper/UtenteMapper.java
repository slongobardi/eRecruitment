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
import it.trefin.erecruitment.model.Preferenza;
import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.model.UtenteTitoliStudio;

public class UtenteMapper {

	public static UtenteDto toDto(Utente u) {
		UtenteDto uDto = new UtenteDto();

		uDto.setId(u.getId());
		uDto.setNome(u.getNome());
		uDto.setCognome(u.getCognome());
		uDto.setEmail(u.getEmail());
		uDto.setPassword(u.getPassword());
		uDto.setCellulare(u.getCellulare());
		uDto.setDescrizione(u.getDescrizione());
		uDto.setIndirizzo(u.getIndirizzo());
		uDto.setCitta(u.getCitta());
		uDto.setVerified(u.isVerified());
		uDto.setCompleted(u.isCompleted());
		uDto.setCodiceFiscale(u.getCodiceFiscale());
		uDto.setTelefono(u.getTelefono());
		uDto.setGenere(u.getGenere());
		uDto.setOrigine(u.getOrigine());
		uDto.setComuneNascita(u.getComuneNascita());
		uDto.setCategoriaProtetta(u.isCategoriaProtetta());
		uDto.setPercentualeInvalidita(u.getPercentualeInvalidita());
		uDto.setFoto(u.getFoto());
		uDto.setCv(u.getCv());
		uDto.setDataModificaCv(u.getDataModificaCv());
		uDto.setDataNascita(u.getDataNascita());
		uDto.setSchedaCandidato(u.getSchedaCandidato() != null
				? u.getSchedaCandidato().stream().map(SchedaCandidato::getId).collect(Collectors.toList())
				: new ArrayList<>());

		uDto.setListaSkill(
				u.getListaSkill() != null ? u.getListaSkill().stream().map(Skill::getId).collect(Collectors.toSet())
						: new HashSet<>());

		uDto.setUtenteTitoliStudio(u.getUtenteTitoliStudio() != null
				? u.getUtenteTitoliStudio().stream().map(UtenteTitoliStudio::getId).collect(Collectors.toList())
				: new ArrayList<>());

		uDto.setListaEsperienze(u.getEsperienze() != null
				? u.getEsperienze().stream().map(Esperienza::getId).collect(Collectors.toList())
				: new ArrayList<>());

		uDto.setUtentiCandidati(u.getUtentiCandidati() != null
				? u.getUtentiCandidati().stream().map(UtenteCandidatura::getId).collect(Collectors.toList())
				: new ArrayList<>());

		uDto.setListaColloquii(u.getListaColloquii()!=null ? u.getListaColloquii().stream().map(ColloquioMapper::toDto).collect(Collectors.toList()): new ArrayList<>());

		uDto.setIdAzienda(u.getAzienda() != null ? u.getAzienda().getId() : -1);

		uDto.setPreferenze(u.getPreferenza() != null
				? u.getPreferenza().stream().map(Preferenza::getId).collect(Collectors.toList())
				: new ArrayList<Long>());

		uDto.setRuolo(u.getRuolo());

		return uDto;
	}

	public static Utente toEntity(UtenteDto uDto, List<UtenteTitoliStudio> listaUTitoliStudio,
			List<UtenteCandidatura> listaUCandidati, List<Colloquio> listaColloqui, Azienda a,
			List<Esperienza> listaEsperienze) {
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
		u.setPercentualeInvalidita(uDto.getPercentualeInvalidita());

		return u;
	}
}
