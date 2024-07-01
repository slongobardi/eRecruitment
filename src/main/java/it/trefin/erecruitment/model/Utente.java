package it.trefin.erecruitment.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String cellulare;
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	private String indirizzo;
	private String citta;
	
	
	@OneToMany(mappedBy="utente")
	private List<UtenteTitoliStudio>utenteTitoliStudio;
	
	
	@OneToMany(mappedBy="utente")
	private List<UtenteCandidatura>utentiCandidati;
	
	@ManyToMany()
	@JoinTable(
			  name = "utenteColloquio", 
			  joinColumns = @JoinColumn(name = "id_utente"), 
			  inverseJoinColumns = @JoinColumn(name = "id_colloquio"))
	private Set<Colloquio>listaColloquii;
	
	
	@Lob
	private byte[] foto;
	@Lob
	private byte[] cv;
	
	
	public Utente() {

	
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


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCellulare() {
		return cellulare;
	}


	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public List<UtenteTitoliStudio> getUtenteTitoliStudio() {
		return utenteTitoliStudio;
	}


	public void setUtenteTitoliStudio(List<UtenteTitoliStudio> utenteTitoliStudio) {
		this.utenteTitoliStudio = utenteTitoliStudio;
	}


	public List<UtenteCandidatura> getUtentiCandidati() {
		return utentiCandidati;
	}


	public void setUtentiCandidati(List<UtenteCandidatura> utentiCandidati) {
		this.utentiCandidati = utentiCandidati;
	}


	public byte[] getFoto() {
		return foto;
	}


	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	public byte[] getCv() {
		return cv;
	}


	public void setCv(byte[] cv) {
		this.cv = cv;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public Set<Colloquio> getListaColloquii() {
		return listaColloquii;
	}


	public void setListaColloquii(Set<Colloquio> listaColloquii) {
		this.listaColloquii = listaColloquii;
	}




	
	
	
}
