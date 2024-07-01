package it.trefin.erecruitment.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tipologia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	
	@OneToMany(mappedBy="tipologia")
	private List<Azienda>listaAziende;
	
	@OneToMany(mappedBy="tipologia")
	private List<Skill>listaSkill;

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

	public List<Azienda> getListaAziende() {
		return listaAziende;
	}

	public void setListaAziende(List<Azienda> listaAziende) {
		this.listaAziende = listaAziende;
	}

	public List<Skill> getListaSkill() {
		return listaSkill;
	}

	public void setListaSkill(List<Skill> listaSkill) {
		this.listaSkill = listaSkill;
	}

	public Tipologia() {
		
	}
	
	
	
}
