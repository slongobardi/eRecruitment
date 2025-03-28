package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	Utente findByEmail(String email);

	@Query(value = "SELECT * FROM utente u WHERE u.ruolo != 3",nativeQuery = true)
	List<Utente> findAllNotUser();

	
	@Query(value = "SELECT DISTINCT u.* " +
	        "FROM utente u " +
	        "LEFT JOIN utente_candidatura uc ON uc.id_utente = u.id " +
	        "LEFT JOIN candidatura c ON uc.id_candidatura = c.id " +
	        "WHERE u.ruolo = 3 " +
	        "AND (c.id_azienda = :idAzienda OR u.azienda_id = :idAzienda) " +
	        "AND (c.is_evento != TRUE OR c.is_evento IS NULL);", nativeQuery = true)
	List<Utente> findAllNormalUser(@Param("idAzienda") long idAzienda);
	
	@Query(value = "SELECT DISTINCT * FROM utente,candidatura,utente_colloquio,colloquio WHERE candidatura.id_azienda = :idAzienda AND colloquio.id_candidatura = candidatura.id AND utente_colloquio.id_colloquio = colloquio.id AND utente.id = utente_colloquio.id_utente;\r\n"
			,nativeQuery = true)
	List<Utente>findUtentePerAzienda(@Param("idAzienda") long idAzienda);

}
