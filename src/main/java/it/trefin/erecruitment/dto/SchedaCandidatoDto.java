package it.trefin.erecruitment.dto;

public class SchedaCandidatoDto {
	private long id;
	private long azienda;
	private long utente;
	private String nota;
	private Boolean picking;
	private Boolean perso;
	private Boolean ingaggiato;

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

	public Boolean getPicking() {
		return picking;
	}

	public void setPicking(Boolean picking) {
		this.picking = picking;
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

}
