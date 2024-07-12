package it.trefin.erecruitment.dto;

import java.util.Set;



public class SkillDto {

	
	private long id;
	private String nome;
	private Set<Long> listaCandidature;
	private Set<Long> listaUtenti;

	
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

	public Set<Long> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(Set<Long> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}


	public Set<Long> getListaUtenti() {
		return listaUtenti;
	}


	public void setListaUtenti(Set<Long> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	
	
}
