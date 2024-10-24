package it.trefin.erecruitment.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TitoliStudio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Studi studi;

	@OneToMany(mappedBy = "titoliStudio", cascade = CascadeType.ALL)
	private List<UtenteTitoliStudio> titoliStudio;

	@ManyToMany(mappedBy = "listaTitoliStudio")
	private Set<Candidatura> listaCandidature;

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

	public Set<Candidatura> getListaCandidature() {
		return listaCandidature;
	}

	public void setListaCandidature(Set<Candidatura> listaCandidature) {
		this.listaCandidature = listaCandidature;
	}
}
