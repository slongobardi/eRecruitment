package it.trefin.erecruitment.dto;

import java.sql.Date;


public class UtenteTitoliStudioDto {

	private long id;

	private long utente;
	
	private long titoliStudio;

	private String descrizione;
	private Date data;
	private int voto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUtente() {
		return utente;
	}
	public void setUtente(long utente) {
		this.utente = utente;
	}
	public long getTitoliStudio() {
		return titoliStudio;
	}
	public void setTitoliStudio(long titoliStudio) {
		this.titoliStudio = titoliStudio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	
}
