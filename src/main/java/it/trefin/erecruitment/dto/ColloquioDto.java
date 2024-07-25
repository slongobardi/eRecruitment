package it.trefin.erecruitment.dto;

import java.sql.Date;
import java.util.Set;

import it.trefin.erecruitment.model.Esito;
import it.trefin.erecruitment.model.Feedback;

public class ColloquioDto {

	private long id;
	private Date ultimoColloquio;
	private Date prossimoColloquio;
	private String nomeEsaminatore;
	private String cognomeEsaminatore;
	private String descrizione;
	private Esito esito;
	private Feedback feedback;
	private Set<Long> listaUtenti;
	private long candidatura;
	private String tipo;

	public long getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(long candidatura) {
		this.candidatura = candidatura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getUltimoColloquio() {
		return ultimoColloquio;
	}

	public void setUltimoColloquio(Date ultimoColloquio) {
		this.ultimoColloquio = ultimoColloquio;
	}

	public Date getProssimoColloquio() {
		return prossimoColloquio;
	}

	public void setProssimoColloquio(Date prossimoColloquio) {
		this.prossimoColloquio = prossimoColloquio;
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

	public Set<Long> getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(Set<Long> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public Esito getEsito() {
		return esito;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	
}
