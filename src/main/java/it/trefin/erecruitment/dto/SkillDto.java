package it.trefin.erecruitment.dto;

import java.util.Set;



public class SkillDto {

	
	private long id;
	private String nome;
	private long tipologia;

	private Set<Long> listaCandidature;

	
	
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

	public long getTipologia() {
		return tipologia;
	}

	public void setTipologia(long tipologia) {
		this.tipologia = tipologia;
	}

	public Set<Long> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(Set<Long> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}

	
	
}
