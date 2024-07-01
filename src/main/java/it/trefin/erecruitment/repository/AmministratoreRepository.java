package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Amministratore;

@Repository
public interface AmministratoreRepository extends JpaRepository<Amministratore,Long>{



}
