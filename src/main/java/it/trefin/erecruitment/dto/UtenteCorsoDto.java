package it.trefin.erecruitment.dto;

import java.sql.Date;

import it.trefin.erecruitment.model.UtenteCorso;

public class UtenteCorsoDto {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private Date dataNascita;
    private String indirizzo;
    private String cellulare;
    private String citta;
    private byte[] cv;
    private Date dataInizio;
    private Date dataFine;
    private String nota;
	private Long corsoId;
	private Long utenteId;

	
	
	
    public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public Date getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}


	public Date getDataFine() {
		return dataFine;
	}


	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}


	public Long getUtenteId() {
		return utenteId;
	}


	public void setUtenteId(Long utenteId) {
		this.utenteId = utenteId;
	}


	public byte[] getCv() {
		return cv;
	}


	public void setCv(byte[] cv) {
		this.cv = cv;
	}


	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}



	
	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getCellulare() {
		return cellulare;
	}


	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
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


	public UtenteCorsoDto() {};
	
	public UtenteCorsoDto(UtenteCorso utenteCorso) {
    	    this.id = utenteCorso.getUtente().getId();
    	    this.utenteId = utenteCorso.getUtente().getId();
    	    this.nome = utenteCorso.getUtente().getNome();
    	    this.cognome = utenteCorso.getUtente().getCognome();
    	    this.email = utenteCorso.getUtente().getEmail();
    	    this.dataNascita = utenteCorso.getUtente().getDataNascita();
    	    this.indirizzo = utenteCorso.getUtente().getIndirizzo();
    	    this.cellulare = utenteCorso.getUtente().getCellulare();
    	    this.citta = utenteCorso.getUtente().getCitta();
    	    this.cv = utenteCorso.getUtente().getCv();
    	    this.dataInizio = utenteCorso.getUtente().getDataInizio();
    	    this.dataFine = utenteCorso.getUtente().getDataFine();
    	    this.nota = utenteCorso.getUtente().getNota();
    	    this.corsoId = utenteCorso.getCorso().getId();   	
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCorsoId() {
		return corsoId;
	}
	public void setCorsoId(Long corsoId) {
		this.corsoId = corsoId;
	}
}
