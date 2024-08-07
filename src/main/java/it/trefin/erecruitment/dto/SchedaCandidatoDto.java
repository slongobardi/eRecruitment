package it.trefin.erecruitment.dto;

public class SchedaCandidatoDto {
	private long id;
	private String nota;
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

}
