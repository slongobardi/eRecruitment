package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{

}
