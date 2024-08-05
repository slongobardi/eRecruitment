package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.SchedaCandidato;

@Repository
public interface SchedaCandidatoRepository extends JpaRepository<SchedaCandidato, Long> {
	
	List<SchedaCandidato> findByPickingTrue();
	List<SchedaCandidato> findByPersoTrue();
	List<SchedaCandidato> findByIngaggiatoTrue();




}
