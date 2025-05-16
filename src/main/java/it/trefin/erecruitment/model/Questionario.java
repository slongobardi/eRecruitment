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

	private int java;
	private int javascript;
	private int dotnet;
	private String altro;
	private String altroSkill;
	private String altroSoftware;
	private int annoDiploma;
	private int autocad;
	private int c;
	private int catia;
	private int gira;
	private int cpp;
	private int pass;
	private int playwright;
	private int selenium;
	private int angular;
	private String altroAmbito;
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
	private String argomentoTesi;
	private int automotive;
	private int bancario;
	private int finanziario;
	private int navale;
	private int aerospace;
	private int militare;
	private int entertainment;
	private int ferroviario;
	private int aeronautico;
	private int spaziale;
	private int elettrico;
	private int Biomedicale;
	private int produzione; 
	private int computerVisionMl;
	private int ricercaSviluppo;
	private int webApplication;
	private int sviluppoSoftware;
	private int sistemiReti;
	private int embedded;
	private int testing;
	private int nlp;
	private String altroSettore;
	private int matlab;
	private int simulink;
	private int hypermesh;
	private int abaqus;
	private int creo;
	private int design;
	private int stress;
	private int cfd;
	private int manufacturingSupport;
	private int ingegneriaIndustriale;
	private int disegno2D;
	private int disegno3D;
	private int disegnoConSwCAD;
	private int schemiElettrici;
	private int schemiIdraulici;
	private int schemiVentilazione;
	private int pianoCarpenteria;
	private int pianiGenerali;
	private int microstation;
	private int autocad2D;
	private int autocad3D;
	private int crocieristico;
	private int megaYacht;
	private int offshore;
	private int mercantile;
	private int industrializzazione;
	private int impiantiElettrici;
	private int impiantiIdraulici;
	private int impiantiVentilazioneCond;
	private int struttureNavali;
	private String esamiMancanti;
	private int ansys;
	private String altroAmbitoMerceologico;
	private String altroSettoreTecnologico;
	private String altroSettoreMerceologico;
	private String ambitoEccellenza;
	private boolean jobRotation;
	private boolean mobilitaItalia;
	private boolean mobilitaEstero;
	private boolean mobilitaRegioni;
	private boolean mobilitaNo;
	private String regioniPreferite;
	private String paesiPreferiti;


	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "candidatura_id")
	private Candidatura candidatura;

	public boolean isJobRotation() {
		return jobRotation;
	}

	public void setJobRotation(boolean jobRotation) {
		this.jobRotation = jobRotation;
	}


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

	public int getAeronautico() {
		return aeronautico;
	}

	public void setAeronautico(int aeronautico) {
		this.aeronautico = aeronautico;
	}

	public int getSpaziale() {
		return spaziale;
	}

	public void setSpaziale(int spaziale) {
		this.spaziale = spaziale;
	}

	public int getElettrico() {
		return elettrico;
	}

	public void setElettrico(int elettrico) {
		this.elettrico = elettrico;
	}

	public int getBiomedicale() {
		return Biomedicale;
	}

	public void setBiomedicale(int biomedicale) {
		Biomedicale = biomedicale;
	}

	public int getProduzione() {
		return produzione;
	}

	public void setProduzione(int produzione) {
		this.produzione = produzione;
	}

	public int getDesign() {
		return design;
	}

	public void setDesign(int design) {
		this.design = design;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		this.stress = stress;
	}

	public int getManufacturingSupport() {
		return manufacturingSupport;
	}

	public void setManufacturingSupport(int manufacturingSupport) {
		this.manufacturingSupport = manufacturingSupport;
	}

	public int getIngegneriaIndustriale() {
		return ingegneriaIndustriale;
	}

	public void setIngegneriaIndustriale(int ingegneriaIndustriale) {
		this.ingegneriaIndustriale = ingegneriaIndustriale;
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
	public int getSqll() {
		return sqll;
	}

	public void setSqll(int sqll) {
		this.sqll = sqll;
	}

	public String getArgomentoTesi() {
		return argomentoTesi;
	}

	public void setArgomentoTesi(String argomentoTesi) {
		this.argomentoTesi = argomentoTesi;
	}

	public int getAutomotive() {
		return automotive;
	}

	public void setAutomotive(int automotive) {
		this.automotive = automotive;
	}

	public int getBancario() {
		return bancario;
	}

	public void setBancario(int bancario) {
		this.bancario = bancario;
	}

	public int getFinanziario() {
		return finanziario;
	}

	public void setFinanziario(int finanziario) {
		this.finanziario = finanziario;
	}

	public int getNavale() {
		return navale;
	}

	public void setNavale(int navale) {
		this.navale = navale;
	}

	public int getAerospace() {
		return aerospace;
	}

	public void setAerospace(int aerospace) {
		this.aerospace = aerospace;
	}

	public int getMilitare() {
		return militare;
	}

	public void setMilitare(int militare) {
		this.militare = militare;
	}

	public int getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(int entertainment) {
		this.entertainment = entertainment;
	}

	public int getFerroviario() {
		return ferroviario;
	}

	public void setFerroviario(int ferroviario) {
		this.ferroviario = ferroviario;
	}

	public int getComputerVisionMl() {
		return computerVisionMl;
	}

	public void setComputerVisionMl(int computerVisionMl) {
		this.computerVisionMl = computerVisionMl;
	}

	public int getRicercaSviluppo() {
		return ricercaSviluppo;
	}

	public void setRicercaSviluppo(int ricercaSviluppo) {
		this.ricercaSviluppo = ricercaSviluppo;
	}

	public int getWebApplication() {
		return webApplication;
	}

	public void setWebApplication(int webApplication) {
		this.webApplication = webApplication;
	}

	public int getSviluppoSoftware() {
		return sviluppoSoftware;
	}

	public void setSviluppoSoftware(int sviluppoSoftware) {
		this.sviluppoSoftware = sviluppoSoftware;
	}

	public int getSistemiReti() {
		return sistemiReti;
	}

	public void setSistemiReti(int sistemiReti) {
		this.sistemiReti = sistemiReti;
	}

	public int getEmbedded() {
		return embedded;
	}

	public void setEmbedded(int embedded) {
		this.embedded = embedded;
	}

	public int getTesting() {
		return testing;
	}

	public void setTesting(int testing) {
		this.testing = testing;
	}

	public int getNlp() {
		return nlp;
	}

	public void setNlp(int nlp) {
		this.nlp = nlp;
	}

	public String getAltroSettore() {
		return altroSettore;
	}

	public void setAltroSettore(String altroSettore) {
		this.altroSettore = altroSettore;
	}

	public int getMatlab() {
		return matlab;
	}

	public void setMatlab(int matlab) {
		this.matlab = matlab;
	}

	public int getSimulink() {
		return simulink;
	}

	public void setSimulink(int simulink) {
		this.simulink = simulink;
	}

	public int getHypermesh() {
		return hypermesh;
	}

	public void setHypermesh(int hypermesh) {
		this.hypermesh = hypermesh;
	}

	public int getDisegno2D() {
		return disegno2D;
	}

	public void setDisegno2D(int disegno2d) {
		disegno2D = disegno2d;
	}

	public int getDisegno3D() {
		return disegno3D;
	}

	public void setDisegno3D(int disegno3d) {
		disegno3D = disegno3d;
	}

	public int getDisegnoConSwCAD() {
		return disegnoConSwCAD;
	}

	public void setDisegnoConSwCAD(int disegnoConSwCAD) {
		this.disegnoConSwCAD = disegnoConSwCAD;
	}

	public int getSchemiElettrici() {
		return schemiElettrici;
	}

	public void setSchemiElettrici(int schemiElettrici) {
		this.schemiElettrici = schemiElettrici;
	}

	public int getSchemiIdraulici() {
		return schemiIdraulici;
	}

	public void setSchemiIdraulici(int schemiIdraulici) {
		this.schemiIdraulici = schemiIdraulici;
	}

	public int getSchemiVentilazione() {
		return schemiVentilazione;
	}

	public void setSchemiVentilazione(int schemiVentilazione) {
		this.schemiVentilazione = schemiVentilazione;
	}

	public int getPianoCarpenteria() {
		return pianoCarpenteria;
	}

	public void setPianoCarpenteria(int pianoCarpenteria) {
		this.pianoCarpenteria = pianoCarpenteria;
	}

	public int getPianiGenerali() {
		return pianiGenerali;
	}

	public void setPianiGenerali(int pianiGenerali) {
		this.pianiGenerali = pianiGenerali;
	}

	public int getMicrostation() {
		return microstation;
	}

	public void setMicrostation(int microstation) {
		this.microstation = microstation;
	}

	public int getAutocad2D() {
		return autocad2D;
	}

	public void setAutocad2D(int autocad2d) {
		autocad2D = autocad2d;
	}
	
	public int getCrocieristico() {
		return crocieristico;
	}

	public void setCrocieristico(int crocieristico) {
		this.crocieristico = crocieristico;
	}

	public int getMegaYacht() {
		return megaYacht;
	}

	public void setMegaYacht(int megaYacht) {
		this.megaYacht = megaYacht;
	}

	public int getOffshore() {
		return offshore;
	}

	public void setOffshore(int offshore) {
		this.offshore = offshore;
	}

	public int getMercantile() {
		return mercantile;
	}

	public void setMercantile(int mercantile) {
		this.mercantile = mercantile;
	}

	public int getIndustrializzazione() {
		return industrializzazione;
	}

	public void setIndustrializzazione(int industrializzazione) {
		this.industrializzazione = industrializzazione;
	}

	public int getImpiantiElettrici() {
		return impiantiElettrici;
	}

	public void setImpiantiElettrici(int impiantiElettrici) {
		this.impiantiElettrici = impiantiElettrici;
	}

	public int getImpiantiIdraulici() {
		return impiantiIdraulici;
	}

	public void setImpiantiIdraulici(int impiantiIdraulici) {
		this.impiantiIdraulici = impiantiIdraulici;
	}

	public int getImpiantiVentilazioneCond() {
		return impiantiVentilazioneCond;
	}

	public void setImpiantiVentilazioneCond(int impiantiVentilazioneCond) {
		this.impiantiVentilazioneCond = impiantiVentilazioneCond;
	}

	public int getStruttureNavali() {
		return struttureNavali;
	}

	public void setStruttureNavali(int struttureNavali) {
		this.struttureNavali = struttureNavali;
	}

	public int getGira() {
		return gira;
	}

	public void setGira(int gira) {
		this.gira = gira;
	}

	public int getCpp() {
		return cpp;
	}

	public void setCpp(int cpp) {
		this.cpp = cpp;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getPlaywright() {
		return playwright;
	}

	public void setPlaywright(int playwright) {
		this.playwright = playwright;
	}

	public int getSelenium() {
		return selenium;
	}

	public void setSelenium(int selenium) {
		this.selenium = selenium;
	}

	public int getAngular() {
		return angular;
	}

	public void setAngular(int angular) {
		this.angular = angular;
	}

	public String getAltroAmbito() {
		return altroAmbito;
	}

	public void setAltroAmbito(String altroAmbito) {
		this.altroAmbito = altroAmbito;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getJavascript() {
		return javascript;
	}

	public void setJavascript(int javascript) {
		this.javascript = javascript;
	}

	public int getDotnet() {
		return dotnet;
	}

	public void setDotnet(int dotnet) {
		this.dotnet = dotnet;
	}

	public String getEsamiMancanti() {
		return esamiMancanti;
	}

	public void setEsamiMancanti(String esamiMancanti) {
		this.esamiMancanti = esamiMancanti;
	}

	public int getAnsys() {
		return ansys;
	}

	public void setAnsys(int ansys) {
		this.ansys = ansys;
	}

	public String getAltroAmbitoMerceologico() {
		return altroAmbitoMerceologico;
	}

	public void setAltroAmbitoMerceologico(String altroAmbitoMerceologico) {
		this.altroAmbitoMerceologico = altroAmbitoMerceologico;
	}

	public String getAltroSettoreTecnologico() {
		return altroSettoreTecnologico;
	}

	public void setAltroSettoreTecnologico(String altroSettoreTecnologico) {
		this.altroSettoreTecnologico = altroSettoreTecnologico;
	}

	public String getAltroSettoreMerceologico() {
		return altroSettoreMerceologico;
	}

	public void setAltroSettoreMerceologico(String altroSettoreMerceologico) {
		this.altroSettoreMerceologico = altroSettoreMerceologico;
	}

	public int getCatia() {
		return catia;
	}

	public int getRhinoceros() {
		return rhinoceros;
	}

	public void setRhinoceros(int rhinoceros) {
		this.rhinoceros = rhinoceros;
	}

	public int getAutocad3D() {
		return autocad3D;
	}

	public void setAutocad3D(int autocad3d) {
		autocad3D = autocad3d;
	}

	public void setCatia(int catia) {
		this.catia = catia;
	}

	public String getRegioniPreferite() {
		return regioniPreferite;
	}

	public void setRegioniPreferite(String regioniPreferite) {
		this.regioniPreferite = regioniPreferite;
	}

	public String getPaesiPreferiti() {
		return paesiPreferiti;
	}

	public void setPaesiPreferiti(String paesiPreferiti) {
		this.paesiPreferiti = paesiPreferiti;
	}

	public String getAmbitoEccellenza() {
		return ambitoEccellenza;
	}

	public void setAmbitoEccellenza(String ambitoEccellenza) {
		this.ambitoEccellenza = ambitoEccellenza;
	}

	public int getAbaqus() {
		return abaqus;
	}

	public void setAbaqus(int abaqus) {
		this.abaqus = abaqus;
	}

	public int getCreo() {
		return creo;
	}

	public void setCreo(int creo) {
		this.creo = creo;
	}

	public int getCfd() {
		return cfd;
	}

	public void setCfd(int cfd) {
		this.cfd = cfd;
	}

	public boolean isMobilitaItalia() {
		return mobilitaItalia;
	}

	public void setMobilitaItalia(boolean mobilitaItalia) {
		this.mobilitaItalia = mobilitaItalia;
	}

	public boolean isMobilitaNo() {
		return mobilitaNo;
	}

	public void setMobilitaNo(boolean mobilitaNo) {
		this.mobilitaNo = mobilitaNo;
	}

	public boolean isMobilitaEstero() {
		return mobilitaEstero;
	}

	public void setMobilitaEstero(boolean mobilitaEstero) {
		this.mobilitaEstero = mobilitaEstero;
	}

	public boolean isMobilitaRegioni() {
		return mobilitaRegioni;
	}

	public void setMobilitaRegioni(boolean mobilitaRegioni) {
		this.mobilitaRegioni = mobilitaRegioni;
	}


}
