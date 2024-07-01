package it.trefin.erecruitment.dto;

import java.sql.Date;
import java.util.Set;



public class ColloquioDto {

	private long id;

	private Date ultimoColloquio;
	private Date prossimoColloquio;
	private String nomeEsaminatore;
	private String cognomeEsaminatore;
	private String esito;	
	private Set<Long> listaUtenti;
	
	
	
	
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
	public Set<Long> getListaUtenti() {
		return listaUtenti;
	}
	public void setListaUtenti(Set<Long> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	
	
	
}
