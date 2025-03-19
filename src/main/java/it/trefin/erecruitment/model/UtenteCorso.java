package it.trefin.erecruitment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UtenteCorso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	 @ManyToOne
	    @JoinColumn(name = "utente_id", nullable = false)
	    private Utente utente;

	    @ManyToOne
	    @JoinColumn(name = "corso_id", nullable = false)
	    private Corso corso;

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

		public Corso getCorso() {
			return corso;
		}

		public void setCorso(Corso corso) {
			this.corso = corso;
		}

}
