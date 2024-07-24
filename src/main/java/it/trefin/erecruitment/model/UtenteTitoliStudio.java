package it.trefin.erecruitment.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private int voto;
	
	
	public UtenteTitoliStudio() {

	
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getVoto() {
		return voto;
	}


	public void setVoto(int voto) {
		this.voto = voto;
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
