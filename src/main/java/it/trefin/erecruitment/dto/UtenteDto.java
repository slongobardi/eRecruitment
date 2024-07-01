package it.trefin.erecruitment.dto;

import java.util.List;
import java.util.Set;




public class UtenteDto {

	private long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String cellulare;
	private String descrizione;
	private String indirizzo;
	private String citta;
	
	private List<Long>utenteTitoliStudio;
	private List<Long>utentiCandidati;
	private Set<Long>listaColloquii;


	private byte[] foto;
	private byte[] cv;
	
	
	
	
	
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
	public List<Long> getUtenteTitoliStudio() {
		return utenteTitoliStudio;
	}
	public void setUtenteTitoliStudio(List<Long> utenteTitoliStudio) {
		this.utenteTitoliStudio = utenteTitoliStudio;
	}
	public List<Long> getUtentiCandidati() {
		return utentiCandidati;
	}
	public void setUtentiCandidati(List<Long> utentiCandidati) {
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
	public Set<Long> getListaColloquii() {
		return listaColloquii;
	}
	public void setListaColloquii(Set<Long> listaColloquii) {
		this.listaColloquii = listaColloquii;
	}
	
	
	

}
