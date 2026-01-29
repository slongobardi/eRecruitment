package it.trefin.erecruitment.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Colloquio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date dataColloquio;
	private String nomeEsaminatore;
	private String cognomeEsaminatore;
	private String tipo;
	private Esito esito;
	private Feedback feedback;

	@Column(columnDefinition = "TEXT")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name="id_utente")
	private Utente utente;

	@ManyToOne()
	@JoinColumn(name = "id_candidatura")
	private Candidatura candidatura;

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

	public Date getDataColloquio() {
		return dataColloquio;
	}

	public void setDataColloquio(Date dataColloquio) {
		this.dataColloquio = dataColloquio;
	}

	public String getNomeEsaminatore() {
		return nomeEsaminatore;
	}

	public void setNomeEsaminatore(String nomeEsaminatore) {
		this.nomeEsaminatore = nomeEsaminatore;
	}

	public String getCognomeEsaminatore() {
		return cognomeEsaminatore;
	}

	public void setCognomeEsaminatore(String cognomeEsaminatore) {
		this.cognomeEsaminatore = cognomeEsaminatore;
	}

	

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Colloquio() {

	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

}
