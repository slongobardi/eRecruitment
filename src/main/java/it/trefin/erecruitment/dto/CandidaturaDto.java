package it.trefin.erecruitment.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Lob;

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
    private String nomeAzienda;
    private String settore;
    private String sede;
    private Boolean disabilitato;
    private Boolean isEvento;
    private byte[] logoEvento;
    private String tipoIstruzione; 
    
    

    
	public Boolean getDisabilitato() {
		return disabilitato;
	}

	public void setDisabilitato(Boolean disabilitato) {
		this.disabilitato = disabilitato;
	}

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

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Boolean getIsEvento() {
		return isEvento;
	}

	public void setIsEvento(Boolean isEvento) {
		this.isEvento = isEvento;
	}

	public byte[] getLogoEvento() {
		return logoEvento;
	}

	public void setLogoEvento(byte[] logoEvento) {
		this.logoEvento = logoEvento;
	}

	public String getTipoIstruzione() {
		return tipoIstruzione;
	}

	public void setTipoIstruzione(String tipoIstruzione) {
		this.tipoIstruzione = tipoIstruzione;
	}



		
	
    
}
