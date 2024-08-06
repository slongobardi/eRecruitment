package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Colloquio;

@Repository

public interface ColloquioRepository extends JpaRepository<Colloquio, Long> {

	List<Colloquio> findByCandidaturaId(long idCandidatura);

	List<ColloquioDto> findAllByCandidaturaId(long idCandidatura);

	ArrayList<Colloquio> findByUltimoColloquioBetween(Date start, Date end);

	@Query(value = 
			" SELECT COUNT(colloquio.id),SUM(colloquio.feedback =1),SUM(colloquio.feedback = 0),SUM(colloquio.feedback =2),SUM(schedacandidato.picking = 1),SUM(schedacandidato.perso = 1),SUM(schedacandidato.ingaggiato = 1)"
			+ "FROM colloquio "
			+"JOIN \r\n"
			+ "    candidatura ON colloquio.id_candidatura = candidatura.id\r\n"
			+ "JOIN \r\n"
			+ "    utente_colloquio ON utente_colloquio.id_colloquio = colloquio.id\r\n"
			+ "JOIN \r\n"
			+ "    utente ON utente.id = utente_colloquio.id_utente\r\n"
			+ "JOIN \r\n"
			+ "    schedacandidato ON schedacandidato.id = utente.id_scheda_candidato\r\n" 
			+ "WHERE candidatura.id_azienda = :id && colloquio.ultimo_colloquio BETWEEN :start AND :end ;", nativeQuery = true)
	Object[] totalFeedback(@Param("id") int id, @Param("start") Date startDate, @Param("end") Date endDate);


}
