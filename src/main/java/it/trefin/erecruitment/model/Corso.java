package it.trefin.erecruitment.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"candidatura"})

public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String sede;
    private String settore;
    private String descrizione;
    private Date dataInizio;
    private Date dataFine;

    public Corso() {}
    
    public Corso(Long id, String sede, String settore, String descrizione, String nome, Date dataInizio, Date dataFine, Candidatura candidatura) {
		super();
		this.id = id;
		this.sede = sede;
		this.settore = settore;
		this.descrizione = descrizione;
		this.candidatura = candidatura;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}



	@ManyToOne
    @JoinColumn(name = "candidatura_id", nullable = false)
    private Candidatura candidatura;
    

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

	public Candidatura getCandidatura() {  
        return candidatura;
    }

    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }


    
}
