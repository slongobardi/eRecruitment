package it.trefin.erecruitment.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	private String password;
	private String cellulare;
	private String indirizzo;
	private String citta;
	private Ruolo ruolo;

	@Column(unique = true)
	private String email;
	
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	
	@OneToMany(mappedBy = "utente")
	private List<UtenteTitoliStudio> utenteTitoliStudio;

	@OneToMany(mappedBy = "utente")
	private List<UtenteCandidatura> utentiCandidati;

	@ManyToMany()
	@JoinTable(name = "utenteColloquio", joinColumns = @JoinColumn(name = "id_utente"), inverseJoinColumns = @JoinColumn(name = "id_colloquio"))
	private Set<Colloquio> listaColloquii;
	
	@OneToMany
	private List<Azienda> aziende;

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

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public List<Azienda> getAziende() {
		return aziende;
	}

	public void setAziende(List<Azienda> aziende) {
		this.aziende = aziende;
	}
}
