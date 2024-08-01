package it.trefin.erecruitment.dto;

import java.sql.Date;

public class UtenteTitoliStudioDto {

	private long id;
	private long utente;
	private long titoliStudio;
	private String nomeTitoliStudio;
	private String descrizione;
	private String annoInizio;
	private Date dataConseguimento;
	private int voto;
	private boolean lode;
	private boolean completed;

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

	public String getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(String annoInizio) {
		this.annoInizio = annoInizio;
	}

	public Date getDataConseguimento() {
		return dataConseguimento;
	}

	public void setDataConseguimento(Date dataConseguimento) {
		this.dataConseguimento = dataConseguimento;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public boolean getLode() {
		return lode;
	}

	public void setLode(boolean lode) {
		this.lode = lode;
	}

	public String getNomeTitoliStudio() {
		return nomeTitoliStudio;
	}

	public void setNomeTitoliStudio(String nomeTitoliStudio) {
		this.nomeTitoliStudio = nomeTitoliStudio;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
