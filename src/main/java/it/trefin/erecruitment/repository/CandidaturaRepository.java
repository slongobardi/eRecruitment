package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{

	List<Candidatura> findAllByAziendaId(long id_azienda);

}
