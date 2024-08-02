package it.trefin.erecruitment.dto;


public class PreferenzaDto {
	private long id;
	private long utente;
	private long azienda;
	private String nomeAzienda;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUtente() {
		return utente;
	}

	public void setUtente(long utente) {
		this.utente = utente;
	}

	public long getAzienda() {
		return azienda;
	}

	public void setAzienda(long azienda) {
		this.azienda = azienda;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	
}
