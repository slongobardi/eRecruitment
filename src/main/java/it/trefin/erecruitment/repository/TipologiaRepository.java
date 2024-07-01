package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Tipologia;

@Repository
public interface TipologiaRepository extends JpaRepository<Tipologia, Long>{

}
