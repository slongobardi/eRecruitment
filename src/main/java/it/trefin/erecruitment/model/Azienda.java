package it.trefin.erecruitment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String indirizzo;

	@Column(columnDefinition = "TEXT")
	private String descrizione;
	private String luogo;
	private String linkAzienda;

	@OneToMany(mappedBy = "azienda")
	private List<Candidatura> listaCandidature;

	@OneToMany(mappedBy = "azienda")
	private List<Utente> listaUtenti;

	@Column(unique = true)
	private String nome;

	@OneToMany(mappedBy = "azienda")
	private List<Preferenza> preferenza;

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

	public Azienda() {

	}

	public List<Utente> getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(List<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	public List<Preferenza> getPreferenza() {
		return preferenza;
	}

	public void setPreferenza(List<Preferenza> preferenza) {
		this.preferenza = preferenza;
	}

}
