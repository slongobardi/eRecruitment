package it.trefin.erecruitment.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Azienda {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String indirizzo;
	
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	private String luogo;
	private String linkAzienda;
	
	
	@OneToMany(mappedBy="azienda")
	private List<Candidatura>listaCandidature;
	
	@ManyToOne()
	@JoinColumn(name="id_tipologia")
	private Tipologia tipologia;
	
	@OneToMany(mappedBy="azienda")
	private List<Amministratore>listaAmministratori;

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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getLinkAzienda() {
		return linkAzienda;
	}

	public void setLinkAzienda(String linkAzienda) {
		this.linkAzienda = linkAzienda;
	}

	public List<Candidatura> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(List<Candidatura> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}

	public List<Amministratore> getListaAmministratori() {
		return listaAmministratori;
	}

	public void setListaAmministratori(List<Amministratore> listaAmministratori) {
		this.listaAmministratori = listaAmministratori;
	}

	public Azienda() {
		
	}
	
	
}
