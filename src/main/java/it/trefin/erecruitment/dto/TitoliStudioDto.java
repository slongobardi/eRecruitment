package it.trefin.erecruitment.dto;

import java.util.List;

import it.trefin.erecruitment.model.Studi;


public class TitoliStudioDto {

	
	private long id;
	private Studi studi;
	
	private List<Long>titoliStudio;

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
	
	

}
