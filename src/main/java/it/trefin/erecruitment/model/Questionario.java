package it.trefin.erecruitment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Questionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String altro;
	private String altroSkill;
	private String altroSoftware;
	private int annoDiploma;
	private int autocad;
	private int c;
	private int catia;
	private int html;
	private String indirizzoStudio;
	private int office;
	private int python;
	private int rhinoceros;
	private String situazione;
	private int solidworks;
	private int sqll;
	private Boolean trasferimento;
	private String corsoLaurea;
	private int annoLaurea;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "candidatura_id")
	private Candidatura candidatura;

	public String getAltro() {
		return altro;
	}

	public void setAltro(String altro) {
		this.altro = altro;
	}

	public String getAltroSkill() {
		return altroSkill;
	}

	public void setAltroSkill(String altroSkill) {
		this.altroSkill = altroSkill;
	}

	public String getAltroSoftware() {
		return altroSoftware;
	}

	public void setAltroSoftware(String altroSoftware) {
		this.altroSoftware = altroSoftware;
	}

	public int getAnnoDiploma() {
		return annoDiploma;
	}

	public void setAnnoDiploma(int annoDiploma) {
		this.annoDiploma = annoDiploma;
	}

	public int getAutocad() {
		return autocad;
	}

	public void setAutocad(int autocad) {
		this.autocad = autocad;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getCatia() {
		return catia;
	}

	public void setCatia(int catia) {
		this.catia = catia;
	}

	public int getHtml() {
		return html;
	}

	public void setHtml(int html) {
		this.html = html;
	}

	public String getIndirizzoStudio() {
		return indirizzoStudio;
	}

	public void setIndirizzoStudio(String indirizzoStudio) {
		this.indirizzoStudio = indirizzoStudio;
	}

	public int getOffice() {
		return office;
	}

	public void setOffice(int office) {
		this.office = office;
	}

	public int getPython() {
		return python;
	}

	public void setPython(int python) {
		this.python = python;
	}

	public int getRhinoceros() {
		return rhinoceros;
	}

	public void setRhinoceros(int rhinoceros) {
		this.rhinoceros = rhinoceros;
	}

	public String getSituazione() {
		return situazione;
	}

	public void setSituazione(String situazione) {
		this.situazione = situazione;
	}

	public int getSolidworks() {
		return solidworks;
	}

	public void setSolidworks(int solidworks) {
		this.solidworks = solidworks;
	}

	public int getSql() {
		return sqll;
	}

	public void setSql(int sqll) {
		this.sqll = sqll;
	}

	public Boolean getTrasferimento() {
		return trasferimento;
	}

	public void setTrasferimento(Boolean trasferimento) {
		this.trasferimento = trasferimento;
	}

	public Questionario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCorsoLaurea() {
		return corsoLaurea;
	}

	public void setCorsoLaurea(String corsoLaurea) {
		this.corsoLaurea = corsoLaurea;
	}

	public int getAnnoLaurea() {
		return annoLaurea;
	}

	public void setAnnoLaurea(int annoLaurea) {
		this.annoLaurea = annoLaurea;
	}

}
