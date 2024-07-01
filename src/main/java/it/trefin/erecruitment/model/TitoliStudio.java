package it.trefin.erecruitment.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TitoliStudio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Studi studi;
	
	
	@OneToMany(mappedBy="titoliStudio")
	private List<UtenteTitoliStudio>titoliStudio;


	public TitoliStudio() {

	
	}


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


	public List<UtenteTitoliStudio> getTitoliStudio() {
		return titoliStudio;
	}


	public void setTitoliStudio(List<UtenteTitoliStudio> titoliStudio) {
		this.titoliStudio = titoliStudio;
	}


	

	


}
