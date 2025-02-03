	package it.trefin.erecruitment.model;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nome;
    private String cognome;
    private String password;
    private String cellulare;
    private String indirizzo;
    private String citta;
    private Date dataNascita;
    private String codiceFiscale;
    private String telefono;
    @Column(nullable = true)
    private String genere;
    private String origine;
    private String comuneNascita;
    private boolean verified;
    private boolean completed;
    private Date dataModificaCv;
    
    
    @Enumerated(EnumType.ORDINAL)
    private Ruolo ruolo;
    
    @Column(unique = true)
    @Email(message = "Inserisci una email valida.")
    private String email;
    
    @Column(columnDefinition = "TEXT")
    private String descrizione;
    
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<UtenteTitoliStudio> utenteTitoliStudio;
    
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<UtenteCandidatura> utentiCandidati;
    
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Colloquio> listaColloquii;
    
    @ManyToMany
    @JoinTable(name = "utenteSkill", joinColumns = @JoinColumn(name = "id_utente"), inverseJoinColumns = @JoinColumn(name = "id_skill"))
    private Set<Skill> listaSkill;
    
    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;
    

    
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Preferenza> preferenza;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Esperienza> esperienze;
    
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private List<SchedaCandidato> schedaCandidato;
    
    @Lob
    private byte[] foto;
    
    @Lob
    private byte[] cv;

    private boolean categoriaProtetta;

    @Column(nullable = true)
    private Integer percentualeInvalidita; 

    public Utente() {
        this.password = generateRandomPassword();
    }

    private String generateRandomPassword() {
        int length = 12;
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*()-_=+";
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

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

    
    public Date getDataModificaCv() {
		return dataModificaCv;
	}

	public void setDataModificaCv(Date dataModificaCv) {
		this.dataModificaCv = dataModificaCv;
	}

	public String getCellulare() {
        return cellulare;
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

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
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

    public List<UtenteTitoliStudio> getUtenteTitoliStudio() {
        return utenteTitoliStudio;
    }

    public void setUtenteTitoliStudio(List<UtenteTitoliStudio> utenteTitoliStudio) {
        this.utenteTitoliStudio = utenteTitoliStudio;
    }

    public List<UtenteCandidatura> getUtentiCandidati() {
        return utentiCandidati;
    }

    public void setUtentiCandidati(List<UtenteCandidatura> utentiCandidati) {
        this.utentiCandidati = utentiCandidati;
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

    

    public List<Colloquio> getListaColloquii() {
		return listaColloquii;
	}

	public void setListaColloquii(List<Colloquio> listaColloquii) {
		this.listaColloquii = listaColloquii;
	}

	public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public List<Esperienza> getEsperienze() {
        return esperienze;
    }

    public void setEsperienze(List<Esperienza> esperienze) {
        this.esperienze = esperienze;
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

	public List<Preferenza> getPreferenza() {
		return preferenza;
	}

	public void setPreferenza(List<Preferenza> preferenza) {
		this.preferenza = preferenza;
	}

	public List<SchedaCandidato> getSchedaCandidato() {
		return schedaCandidato;
	}

	public void setSchedaCandidato(List<SchedaCandidato> schedaCandidato) {
		this.schedaCandidato = schedaCandidato;
	}


}
