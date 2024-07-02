package it.trefin.erecruitment.model;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Colloquio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date ultimoColloquio;
	private Date prossimoColloquio;
	private String nomeEsaminatore;
	private String cognomeEsaminatore;

	@Column(columnDefinition = "TEXT")
	private String esito;

	@ManyToMany(mappedBy = "listaColloquii")
	private Set<Utente> listaUtenti;

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

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public Set<Utente> getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(Set<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	public Colloquio() {

	}

}
