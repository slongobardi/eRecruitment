package it.trefin.erecruitment.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UtenteTitoliStudio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne()
	@JoinColumn(name ="id_utente")
	private Utente utente;
	
	@ManyToOne()
	@JoinColumn(name ="id_studi")
	private TitoliStudio titoliStudio;
	
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	private Date data;
	
	
	public UtenteTitoliStudio() {

	
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public TitoliStudio getTitoliStudio() {
		return titoliStudio;
	}


	public void setTitoliStudio(TitoliStudio titoliStudio) {
		this.titoliStudio = titoliStudio;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	
	
	
}
