package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Azienda;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long>{
	Azienda findByNome(String nome);
}
