package it.trefin.erecruitment.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.trefin.erecruitment.model.Ruolo;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UtenteDto {

	private long id;
	private long idAzienda;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String cellulare;
	private String descrizione;
	private String indirizzo;
	private String citta;
    private String codiceFiscale;
    private String telefono;
    private String genere;
    private String origine;
    private String comuneNascita;
	private boolean verified;
	private boolean completed;
	private boolean categoriaProtetta;
	private Integer percentualeInvalidita;
	private List<Long> utenteTitoliStudio;
	private List<Long> utentiCandidati;
	private List<Long> listaEsperienze;
	private Set<Long> listaSkill;
	private List<ColloquioDto> listaColloquii;
	private Ruolo ruolo;
	private byte[] foto;
	private byte[] cv;
	private Date dataNascita;
	private List<Long> preferenze;
	private List<SchedaCandidatoDto> schedaCandidato;
	private Date dataModificaCv;
	private Boolean trasferimento;
	private String situazione;
    private long idQuestionario;
    private String nota;
    private Date dataInizio; 
    private Date dataFine;
    private String stato;
    private Date dataIscrizione;



	public Date getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public UtenteDto() {
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getOrigine() {
		return origine;
	}

	public Date getDataModificaCv() {
		return dataModificaCv;
	}

	public void setDataModificaCv(Date dataModificaCv) {
		this.dataModificaCv = dataModificaCv;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCategoriaProtetta() {
		return categoriaProtetta;
	}

	public void setCategoriaProtetta(boolean categoriaProtetta) {
		this.categoriaProtetta = categoriaProtetta;
	}

	public Integer getPercentualeInvalidita() {
		return percentualeInvalidita;
	}

	public void setPercentualeInvalidita(Integer percentualeInvalidita) {
		this.percentualeInvalidita = percentualeInvalidita;
	}

	public List<Long> getUtenteTitoliStudio() {
		return utenteTitoliStudio;
	}

	public void setUtenteTitoliStudio(List<Long> utenteTitoliStudio) {
		this.utenteTitoliStudio = utenteTitoliStudio;
	}

	public List<Long> getUtentiCandidati() {
		return utentiCandidati;
	}

	public void setUtentiCandidati(List<Long> utentiCandidati) {
		this.utentiCandidati = utentiCandidati;
	}

	public Set<Long> getListaSkill() {
		return listaSkill;
	}

	public void setListaSkill(Set<Long> listaSkill) {
		this.listaSkill = listaSkill;
	}

	

	public List<ColloquioDto> getListaColloquii() {
		return listaColloquii;
	}

	public void setListaColloquii(List<ColloquioDto> listaColloquii) {
		this.listaColloquii = listaColloquii;
	}



	public List<Long> getListaEsperienze() {
		return listaEsperienze;
	}

	public void setListaEsperienze(List<Long> listaEsperienze) {
		this.listaEsperienze = listaEsperienze;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public List<Long> getPreferenze() {
		return preferenze;
	}

	public void setPreferenze(List<Long> preferenze) {
		this.preferenze = preferenze;
	}

	public List<SchedaCandidatoDto> getSchedaCandidato() {
		return schedaCandidato;
	}

	public void setSchedaCandidato(List<SchedaCandidatoDto> schedaCandidato) {
		this.schedaCandidato = schedaCandidato;
	}

	
	public Boolean getTrasferimento() {
		return trasferimento;
	}

	public void setTrasferimento(Boolean trasferimento) {
		this.trasferimento = trasferimento;
	}

	public String getSituazione() {
		return situazione;
	}

	public void setSituazione(String situazione) {
		this.situazione = situazione;
	}

	public long getIdQuestionario() {
		return idQuestionario;
	}

	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public UtenteDto(long id, long idAzienda, String nome, String cognome, String email, String password,
			String cellulare, String descrizione, String indirizzo, String citta, String codiceFiscale, String telefono,
			String genere, String origine, String comuneNascita, boolean verified, boolean completed,
			boolean categoriaProtetta, Integer percentualeInvalidita, List<Long> utenteTitoliStudio,
			List<Long> utentiCandidati, List<Long> listaEsperienze, Set<Long> listaSkill,
			List<ColloquioDto> listaColloquii, Ruolo ruolo, byte[] foto, byte[] cv, Date dataNascita,
			List<Long> preferenze, List<SchedaCandidatoDto> schedaCandidato, Date dataModificaCv, Boolean trasferimento,
			String situazione, long idQuestionario, String nota, Date dataInizio, Date dataFine) {
		super();
		this.id = id;
		this.idAzienda = idAzienda;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.cellulare = cellulare;
		this.descrizione = descrizione;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
		this.genere = genere;
		this.origine = origine;
		this.comuneNascita = comuneNascita;
		this.verified = verified;
		this.completed = completed;
		this.categoriaProtetta = categoriaProtetta;
		this.percentualeInvalidita = percentualeInvalidita;
		this.utenteTitoliStudio = utenteTitoliStudio;
		this.utentiCandidati = utentiCandidati;
		this.listaEsperienze = listaEsperienze;
		this.listaSkill = listaSkill;
		this.listaColloquii = listaColloquii;
		this.ruolo = ruolo;
		this.foto = foto;
		this.cv = cv;
		this.dataNascita = dataNascita;
		this.preferenze = preferenze;
		this.schedaCandidato = schedaCandidato;
		this.dataModificaCv = dataModificaCv;
		this.trasferimento = trasferimento;
		this.situazione = situazione;
		this.idQuestionario = idQuestionario;
		this.nota = nota;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	

	

}
