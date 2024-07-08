package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.dto.UtenteCandidaturaDto;
import it.trefin.erecruitment.model.UtenteCandidatura;

@Repository
public interface UtenteCandidaturaRepository extends JpaRepository<UtenteCandidatura, Long>{

	List<UtenteCandidatura> findAllByUtenteId(long id);
	List<UtenteCandidatura> findAllByCandidaturaId(long id);

}
