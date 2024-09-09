package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trefin.erecruitment.model.Esperienza;

public interface EsperienzaRepository extends JpaRepository<Esperienza, Long> {

	List<Esperienza> findByUtenteId(long id);

}
