package it.trefin.erecruitment.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	private BigDecimal ral;

	@Column(name = "settore")
	private String settore;

	@Column(name = "sede")
	private String sede;

	@OneToMany(mappedBy = "candidatura", cascade = CascadeType.ALL)
	private List<UtenteCandidatura> utenteCandidature;

	@OneToMany(mappedBy = "candidatura", cascade = CascadeType.ALL)
	private List<Colloquio> listaColloqui;

	@ManyToOne()
	@JoinColumn(name = "id_azienda")
	private Azienda azienda;

	@ManyToMany()
	@JoinTable(name = "candidaturaSkill", joinColumns = @JoinColumn(name = "id_candidatura"), inverseJoinColumns = @JoinColumn(name = "id_skill"))
	private Set<Skill> listaSkill;

	@ManyToMany()
	@JoinTable(name = "candidaturaTitoliStudio", joinColumns = @JoinColumn(name = "id_candidatura"), inverseJoinColumns = @JoinColumn(name = "id_titoliStudio"))
	private Set<TitoliStudio> listaTitoliStudio;

	private Boolean disabilitato;

	private Boolean isEvento;

	@OneToMany(mappedBy = "candidatura", cascade = CascadeType.ALL)
	private List<Questionario> questionari;
	
	@Lob
	private byte[] logoEvento;

	// Getters and setters

	public long getId() {
		return id;
	}

	public Boolean getDisabilitato() {
		return disabilitato;
	}

	public void setDisabilitato(Boolean disabilitato) {
		this.disabilitato = disabilitato;
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

	public BigDecimal getRal() {
		return ral;
	}

	public void setRal(BigDecimal ral) {
		this.ral = ral;
	}

	public List<UtenteCandidatura> getUtenteCandidature() {
		return utenteCandidature;
	}

	public void setUtenteCandidature(List<UtenteCandidatura> utenteCandidature) {
		this.utenteCandidature = utenteCandidature;
	}

	public List<Colloquio> getListaColloqui() {
		return listaColloqui;
	}

	public void setListaColloqui(List<Colloquio> listaColloqui) {
		this.listaColloqui = listaColloqui;
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

	public Set<TitoliStudio> getListaTitoliStudio() {
		return listaTitoliStudio;
	}

	public void setListaTitoliStudio(Set<TitoliStudio> listaTitoliStudio) {
		this.listaTitoliStudio = listaTitoliStudio;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Candidatura() {
	}

	public Boolean getIsEvento() {
		return isEvento;
	}

	public void setIsEvento(Boolean isEvento) {
		this.isEvento = isEvento;
	}

	public byte[] getLogoEvento() {
		return logoEvento;
	}

	public void setLogoEvento(byte[] logoEvento) {
		this.logoEvento = logoEvento;
	}

	public List<Questionario> getQuestionari() {
		return questionari;
	}

	public void setQuestionari(List<Questionario> questionari) {
		this.questionari = questionari;
	}

}
