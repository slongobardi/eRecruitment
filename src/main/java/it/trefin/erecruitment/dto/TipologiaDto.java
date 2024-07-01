package it.trefin.erecruitment.dto;

import java.util.List;



public class TipologiaDto {

	
	private long id;
	private String nome;
	
	private List<Long>listaAziende;
	
	private List<Long>listaSkill;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Long> getListaAziende() {
		return listaAziende;
	}

	public void setListaAziende(List<Long> listaAziende) {
		this.listaAziende = listaAziende;
	}

	public List<Long> getListaSkill() {
		return listaSkill;
	}

	public void setListaSkill(List<Long> listaSkill) {
		this.listaSkill = listaSkill;
	}
	
	
}
