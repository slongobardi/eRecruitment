package it.trefin.erecruitment.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.CandidaturaDto;
import it.trefin.erecruitment.model.Azienda;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.UtenteCandidatura;

public class CandidaturaMapper {

    public static CandidaturaDto toDto(Candidatura c) {
        CandidaturaDto cDto = new CandidaturaDto();

        cDto.setDescrizione(c.getDescrizione());
        cDto.setId(c.getId());
        cDto.setNome(c.getNome());
        cDto.setNomeAzienda(c.getAzienda().getNome());
        cDto.setNumeroCandidati(c.getNumeroCandidati());
        cDto.setPubblicazione(c.getPubblicazione());
        cDto.setRal(c.getRal());
        cDto.setSettore(c.getSettore());
        cDto.setSede(c.getSede());
        cDto.setDisabilitato(c.getDisabilitato());
        cDto.setIsEvento(c.getIsEvento());
        cDto.setIsAcademy(c.getIsAcademy());
        cDto.setTipoIstruzione(c.getTipoIstruzione());
        cDto.setDataInizio(c.getDataInizio());
        cDto.setDataFine(c.getDataFine());
        if(c.getLogoEvento()!=null) {
        if(c.getIsEvento() && c.getLogoEvento()!=null) {
        cDto.setLogoEvento(c.getLogoEvento());}
        else {
        	cDto.setLogoEvento(null);
        }}
        cDto.setListaColloqui(
            c.getListaColloqui() != null ? 
            c.getListaColloqui().stream().map(Colloquio::getId).collect(Collectors.toList()) : 
            new ArrayList<>()
        );
        cDto.setAzienda(
            c.getAzienda() != null ? 
            c.getAzienda().getId() : 
            -1
        );

        cDto.setListaSkill(
            c.getListaSkill() != null ? 
            c.getListaSkill().stream().map(Skill::getId).collect(Collectors.toSet()) : 
            new HashSet<>()
        );

        cDto.setUtenteCandidature(
            c.getUtenteCandidature() != null ? 
            c.getUtenteCandidature().stream().map(UtenteCandidatura::getId).collect(Collectors.toList()) : 
            new ArrayList<>()
        );

        cDto.setListaTitoliStudio(
            c.getListaTitoliStudio() != null ? 
            c.getListaTitoliStudio().stream().map(TitoliStudio::getId).collect(Collectors.toSet()) : 
            new HashSet<>()
        );
        return cDto;
    }

    public static Candidatura toEntity(CandidaturaDto cDto, Azienda azienda, List<UtenteCandidatura> utenteCandidature, Set<Skill> listaSkill, Set<TitoliStudio> listaTitoliStudio) {
        Candidatura c = new Candidatura();
        c.setDescrizione(cDto.getDescrizione());
        c.setId(cDto.getId());
        c.setNome(cDto.getNome());
        c.setNumeroCandidati(cDto.getNumeroCandidati());
        c.setPubblicazione(cDto.getPubblicazione());
        c.setAzienda(azienda);
        c.setUtenteCandidature(utenteCandidature);
        c.setListaSkill(listaSkill);
        c.setListaTitoliStudio(listaTitoliStudio);
        c.setRal(cDto.getRal());
        c.setSettore(cDto.getSettore());
        c.setSede(cDto.getSede());
        c.setDisabilitato(cDto.getDisabilitato());
        c.setIsEvento(cDto.getIsEvento());
        c.setLogoEvento(cDto.getLogoEvento());
        c.setTipoIstruzione(cDto.getTipoIstruzione());
        c.setDataInizio(cDto.getDataInizio());
        c.setDataFine(cDto.getDataFine());
        c.setIsAcademy(cDto.getIsAcademy());
        return c;
    }
}
