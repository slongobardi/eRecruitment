package it.trefin.erecruitment.dto;

public class SchedaCandidatoDto {
	private long id;
	private long azienda;
	private long utente;
	private String nota;
	private Boolean perso;
	private Boolean ingaggiato;
	private Boolean pickingList;
	private Boolean rifiutato;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Boolean getPerso() {
		return perso;
	}

	public void setPerso(Boolean perso) {
		this.perso = perso;
	}

	public Boolean getIngaggiato() {
		return ingaggiato;
	}

	public void setIngaggiato(Boolean ingaggiato) {
		this.ingaggiato = ingaggiato;
	}

	public long getAzienda() {
		return azienda;
	}

	public void setAzienda(long azienda) {
		this.azienda = azienda;
	}

	public long getUtente() {
		return utente;
	}

	public void setUtente(long utente) {
		this.utente = utente;
	}

	public Boolean getPickingList() {
		return pickingList;
	}

	public void setPickingList(Boolean pickingList) {
		this.pickingList = pickingList;
	}

	public Boolean getRifiutato() {
		return rifiutato;
	}

	public void setRifiutato(Boolean rifiutato) {
		this.rifiutato = rifiutato;
	}
	
	
	

}
