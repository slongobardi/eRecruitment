package it.trefin.erecruitment.dto;



import it.trefin.erecruitment.model.Contratto;

public class EsperienzaDto {

	private long id;
	private String nome;
	private String azienda;
	private Contratto contratto;
	private String descrizione;
	private long utente;
	
	
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
	public String getAzienda() {
		return azienda;
	}
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	public Contratto getContratto() {
		return contratto;
	}
	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public long getUtente() {
		return utente;
	}
	public void setUtente(long utente) {
		this.utente = utente;
	}
	
	
	
	
}
