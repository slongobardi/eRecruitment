package it.trefin.erecruitment.dto;

import java.sql.Date;
import java.util.List;

public class CorsoDto {
    private Long id;
    private String nome;
    private String sede;
    private String settore;
    private String descrizione;
    private String logo;
    private Date dataInizio;
    private Date dataFine;

    private Long candidaturaId;
    
    private List<UtenteDto> utenti;


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Long getCandidaturaId() {
		return candidaturaId;
	}

	public void setCandidaturaId(Long candidaturaId) {
		this.candidaturaId = candidaturaId;
	}

	public List<UtenteDto> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<UtenteDto> utenti) {
		this.utenti = utenti;
	}
}
