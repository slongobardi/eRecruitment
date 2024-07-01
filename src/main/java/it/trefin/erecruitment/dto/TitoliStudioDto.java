package it.trefin.erecruitment.dto;

import java.util.List;
import java.util.Set;

import it.trefin.erecruitment.model.Studi;


public class TitoliStudioDto {

	
	private long id;
	private Studi studi;
	
	private List<Long>titoliStudio;
	private Set<Long>listaCandidature;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Studi getStudi() {
		return studi;
	}

	public void setStudi(Studi studi) {
		this.studi = studi;
	}

	public List<Long> getTitoliStudio() {
		return titoliStudio;
	}

	public void setTitoliStudio(List<Long> titoliStudio) {
		this.titoliStudio = titoliStudio;
	}

	public Set<Long> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(Set<Long> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}
	
	

}
