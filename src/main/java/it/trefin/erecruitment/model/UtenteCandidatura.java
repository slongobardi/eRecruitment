package it.trefin.erecruitment.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UtenteCandidatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date dataIscrizione;
	private Stato stato;
	
	@Column(columnDefinition = "TEXT")
	private String nota;
	
	@ManyToOne()
	@JoinColumn(name ="id_Utente")
	private Utente utente;
	
	@ManyToOne()
	@JoinColumn(name ="id_Candidatura")
	private Candidatura candidatura;
	
	private Boolean disabilitato;

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

	public Date getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String note) {
		this.nota = note;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public UtenteCandidatura() {
		
	}

	
	
	
	
	
	
}
