package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Corso;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long>{
}
