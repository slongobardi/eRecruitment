package it.trefin.erecruitment.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class CandidaturaDto {

    private long id;
    private String nome;
    private Date pubblicazione;
    private int numeroCandidati;
    private String descrizione;
    private BigDecimal ral; 
    private List<Long> utenteCandidature;
    private long azienda;
    private Set<Long> listaSkill;
    private Set<Long> listaTitoliStudio;
    private List<Long> listaColloqui;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getPubblicazione() {
        return pubblicazione;
    }

    public void setPubblicazione(Date pubblicazione) {
        this.pubblicazione = pubblicazione;
    }

    public int getNumeroCandidati() {
        return numeroCandidati;
    }

    public void setNumeroCandidati(int numeroCandidati) {
        this.numeroCandidati = numeroCandidati;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getRal() {
        return ral;
    }

    public void setRal(BigDecimal ral) {
        this.ral = ral;
    }

    public List<Long> getUtenteCandidature() {
        return utenteCandidature;
    }

    public void setUtenteCandidature(List<Long> utenteCandidature) {
        this.utenteCandidature = utenteCandidature;
    }

    public long getAzienda() {
        return azienda;
    }

    public void setAzienda(long azienda) {
        this.azienda = azienda;
    }

    public Set<Long> getListaSkill() {
        return listaSkill;
    }

    public void setListaSkill(Set<Long> listaSkill) {
        this.listaSkill = listaSkill;
    }

    public Set<Long> getListaTitoliStudio() {
        return listaTitoliStudio;
    }

    public void setListaTitoliStudio(Set<Long> listaTitoliStudio) {
        this.listaTitoliStudio = listaTitoliStudio;
    }

    public List<Long> getListaColloqui() {
        return listaColloqui;
    }

    public void setListaColloqui(List<Long> listaColloqui) {
        this.listaColloqui = listaColloqui;
    }
}
