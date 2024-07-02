package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{
	Utente findByEmail(String email);
}
