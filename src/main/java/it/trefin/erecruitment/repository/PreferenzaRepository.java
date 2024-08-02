package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trefin.erecruitment.model.Preferenza;

public interface PreferenzaRepository extends JpaRepository<Preferenza, Long> {

	List<Preferenza> findAllByUtenteId(long id);
}
