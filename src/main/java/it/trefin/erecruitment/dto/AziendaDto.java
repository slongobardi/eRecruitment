package it.trefin.erecruitment.dto;

import java.util.List;


public class AziendaDto {

	private long id;
	private String nome;
	private String indirizzo;
	private String descrizione;
	private String luogo;
	private String linkAzienda;
	
	private List<Long>listaCandidature;
	private long tipologia;
	private List<Long>listaAmministratori;
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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public String getLinkAzienda() {
		return linkAzienda;
	}
	public void setLinkAzienda(String linkAzienda) {
		this.linkAzienda = linkAzienda;
	}
	public List<Long> getListaCandidature() {
		return listaCandidature;
	}
	public void setListaCandidature(List<Long> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}
	public long getTipologia() {
		return tipologia;
	}
	public void setTipologia(long tipologia) {
		this.tipologia = tipologia;
	}
	public List<Long> getListaAmministratori() {
		return listaAmministratori;
	}
	public void setListaAmministratori(List<Long> listaAmministratori) {
		this.listaAmministratori = listaAmministratori;
	}
	
	
	

}
