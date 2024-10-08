package it.trefin.erecruitment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedacandidato")
public class SchedaCandidato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nota;
	private Boolean perso = false;
	private Boolean ingaggiato = false;

	@ManyToOne()
	private Utente utente;

	@ManyToOne()
	private Azienda azienda;

	public static SchedaCandidato defaultScheda(Azienda a,Utente u) {
		SchedaCandidato sc = new SchedaCandidato();
		sc.setIngaggiato(false);
		sc.setPerso(false);
		sc.setNota("");
		sc.setAzienda(a);
		sc.setUtente(u);
		return sc;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Boolean getPerso() {
		return perso;
	}

	public void setPerso(Boolean perso) {
		this.perso = perso;
	}

	public Boolean getIngaggiato() {
		return ingaggiato;
	}

	public void setIngaggiato(Boolean ingaggiato) {
		this.ingaggiato = ingaggiato;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
}
