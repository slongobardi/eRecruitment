package it.trefin.erecruitment.model;


import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@ManyToOne()
	@JoinColumn(name="id_tipoligia")
	private Tipologia tipologia;
	
	
	
	@ManyToMany(mappedBy="listaSkill")
	private Set<Candidatura> listaCandidature;

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

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
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
