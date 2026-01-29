package it.trefin.erecruitment.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

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
