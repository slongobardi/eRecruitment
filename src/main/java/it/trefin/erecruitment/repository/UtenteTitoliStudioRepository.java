package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.UtenteTitoliStudio;

@Repository
public interface UtenteTitoliStudioRepository extends JpaRepository<UtenteTitoliStudio, Long> {
	List<UtenteTitoliStudio> findAllByUtenteId(long id_utente);
	UtenteTitoliStudio findByUtenteIdAndTitoliStudioId(long utenteId, long titoliStudioId);
}
