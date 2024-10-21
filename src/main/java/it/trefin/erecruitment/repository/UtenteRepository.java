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

	@Query(value = "SELECT * FROM Utente u WHERE u.ruolo != 3",nativeQuery = true)
	List<Utente> findAllNotUser();

	
	@Query(value = "SELECT DISTINCT utente.*  FROM Utente \r\n"
			+ "JOIN utente_candidatura on utente_candidatura.id_utente=utente.id\r\n"
			+ "join candidatura on utente_candidatura.id_candidatura =candidatura.id\r\n"
			+ "WHERE utente.ruolo = 3\r\n"
			+ "AND candidatura.id_azienda= :idAzienda ;",nativeQuery = true)
	List<Utente> findAllNormalUser(@Param("idAzienda") long idAzienda);
	
	@Query(value = "SELECT DISTINCT * FROM utente,candidatura,utente_colloquio,colloquio WHERE candidatura.id_azienda = :idAzienda AND colloquio.id_candidatura = candidatura.id AND utente_colloquio.id_colloquio = colloquio.id AND utente.id = utente_colloquio.id_utente;\r\n"
			,nativeQuery = true)
	List<Utente>findUtentePerAzienda(@Param("idAzienda") long idAzienda);

}
