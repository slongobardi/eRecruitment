package it.trefin.erecruitment.model;


import java.util.Objects;
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
	
	@ManyToMany(mappedBy="listaSkill")
	private Set<Candidatura> listaCandidature;
	
	@ManyToOne()
	@JoinColumn(name = "id_utente")
	private Utente utente;
	

	@ManyToMany(mappedBy="listaSkill")
	private Set<Utente> listaUtente;
	

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Utente> getListaUtente() {
		return listaUtente;
	}

	public void setListaUtente(Set<Utente> listaUtente) {
		this.listaUtente = listaUtente;
	}


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

	@Override
	public String toString() {
		return "Skill [id=" + id + ", nome=" + nome + ", listaCandidature=" + listaCandidature + ", utente=" + utente
				+ ", listaUtente=" + listaUtente + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return id == other.id && Objects.equals(nome, other.nome);
	}
	
	
}
