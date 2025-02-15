package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.SchedaCandidato;
import it.trefin.erecruitment.model.UtenteCandidatura;

@Repository
public interface SchedaCandidatoRepository extends JpaRepository<SchedaCandidato, Long> {

	List<SchedaCandidato> findByPersoTrue();

	List<SchedaCandidato> findByIngaggiatoTrue();
	List<SchedaCandidato> findAllByAziendaId(long id);
	List<SchedaCandidato> findAllByUtenteId(long id);
	SchedaCandidato findByUtenteIdAndAziendaId(long idU,long idAzienda);

	SchedaCandidato findByUtenteId(long id);

	
}
