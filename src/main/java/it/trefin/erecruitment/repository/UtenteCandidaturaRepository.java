package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.UtenteCandidatura;

@Repository
public interface UtenteCandidaturaRepository extends JpaRepository<UtenteCandidatura, Long>{

}
