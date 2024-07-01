package it.trefin.erecruitment.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Candidatura {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	private Date pubblicazione;
	private int numeroCandidati;

	@Column(columnDefinition = "TEXT")
	private String descrizione;
	
	@OneToMany(mappedBy="candidatura")
	private List<UtenteCandidatura>utenteCandidature;
	
	@ManyToOne()
	@JoinColumn(name="id_azienda")
	private Azienda azienda;
	
	@ManyToMany()
	@JoinTable(
			  name = "candidaturaSkill", 
			  joinColumns = @JoinColumn(name = "id_candidatura"), 
			  inverseJoinColumns = @JoinColumn(name = "id_skill"))
	private Set<Skill>listaSkill;
	
	


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


	public Date getPubblicazione() {
		return pubblicazione;
	}


	public void setPubblicazione(Date pubblicazione) {
		this.pubblicazione = pubblicazione;
	}


	public int getNumeroCandidati() {
		return numeroCandidati;
	}


	public void setNumeroCandidati(int numeroCandidati) {
		this.numeroCandidati = numeroCandidati;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public List<UtenteCandidatura> getUtenteCandidature() {
		return utenteCandidature;
	}


	public void setUtenteCandidature(List<UtenteCandidatura> utenteCandidature) {
		this.utenteCandidature = utenteCandidature;
	}


	public Azienda getAzienda() {
		return azienda;
	}


	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}


	

	public Set<Skill> getListaSkill() {
		return listaSkill;
	}


	public void setListaSkill(Set<Skill> listaSkill) {
		this.listaSkill = listaSkill;
	}


	public Candidatura() {
		
	}
	
	
	
}
