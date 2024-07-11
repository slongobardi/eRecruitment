package it.trefin.erecruitment.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@ManyToMany(mappedBy="listaSkill")
	private Set<Candidatura> listaCandidature;
	

	@ManyToMany(mappedBy="listaSkill")
	private Set<Utente> listaUtente;

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

	public Set<Candidatura> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(Set<Candidatura> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}

	public Skill() {
		
	}
	
	
}
