package it.trefin.erecruitment.dto;

import java.sql.Date;

import it.trefin.erecruitment.model.Stato;



public class UtenteCandidaturaDto {

	
private long id;
	
	private Date dataIscrizione;
	private Stato stato;
	
	private String nota;

	private long utente;
	
	private CandidaturaDto candidatura;

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

	public void setNota(String nota) {
		this.nota = nota;
	}

	public long getUtente() {
		return utente;
	}

	public void setUtente(long utente) {
		this.utente = utente;
	}

	public CandidaturaDto getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(CandidaturaDto candidatura) {
		this.candidatura = candidatura;
	}
	
	
}
