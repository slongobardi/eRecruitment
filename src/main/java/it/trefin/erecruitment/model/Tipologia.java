package it.trefin.erecruitment.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
