package it.trefin.erecruitment.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.model.Colloquio;

@Repository
public interface ColloquioRepository extends JpaRepository<Colloquio, Long> {

	List<Colloquio> findByCandidaturaId(long idCandidatura);

	List<ColloquioDto> findAllByCandidaturaId(long idCandidatura);

	ArrayList<Colloquio> findByUltimoColloquioBetween(Date start, Date end);

	@Query(value = "SELECT\r\n" 
			+ "    COUNT(DISTINCT colloquio.id), \r\n" 
			+ "    SUM(DISTINCT colloquio.feedback = 1), \r\n"
			+ "    SUM(DISTINCT colloquio.feedback = 0), \r\n" 
			+ "    SUM(DISTINCT schedacandidato.perso = 1), \r\n"
			+ "    SUM(DISTINCT schedacandidato.ingaggiato = 1) \r\n" 
			+ "FROM colloquio \r\n"
			+ "JOIN candidatura ON colloquio.id_candidatura = candidatura.id \r\n"
			+ "JOIN utente_colloquio ON utente_colloquio.id_colloquio = colloquio.id \r\n"
			+ "JOIN utente ON utente.id = utente_colloquio.id_utente \r\n"
			+ "JOIN schedacandidato ON schedacandidato.azienda_id = :id \r\n" 
			+ "WHERE \r\n"
			+ "    candidatura.id_azienda = :id AND colloquio.ultimo_colloquio IS NOT NULL AND colloquio.ultimo_colloquio BETWEEN :start AND :end ;", nativeQuery = true)
	Object[] totalFeedback(@Param("id") long id, @Param("start") Date startDate, @Param("end") Date endDate);

	@Query(value = "SELECT DISTINCT\r\n"
			+ "    utente.id,\r\n"
			+ "    utente.nome,\r\n"
			+ "    utente.cognome,\r\n"
			+ "    colloquio.feedback,\r\n"
			+ "    colloquio.descrizione,\r\n"
			+ "    colloquio.tipo,\r\n"
			+ "    schedacandidato.ingaggiato\r\n"
			+ "FROM \r\n"
			+ "    utente\r\n"
			+ "JOIN \r\n"
			+ "    utente_colloquio ON utente.id = utente_colloquio.id_utente\r\n"
			+ "JOIN \r\n"
			+ "    colloquio ON utente_colloquio.id_colloquio = colloquio.id\r\n"
			+ "JOIN \r\n"
			+ "    candidatura ON colloquio.id_candidatura = candidatura.id\r\n"
			+ "JOIN \r\n"
			+ "    schedacandidato ON schedacandidato.utente_id = utente.id\r\n"
			+ "WHERE \r\n"
			+ "    candidatura.id_azienda = :id AND colloquio.ultimo_colloquio BETWEEN :start AND :end ;\r\n"
			+ "", nativeQuery = true)
	Set<Object> reportCandidati(@Param("id") long id, @Param("start") Date startDate, @Param("end") Date endDate);
	
	@Query(value="SELECT DISTINCT\r\n"
			+ "    *\r\n"
			+ "FROM\r\n"
			+ "    colloquio c\r\n"
			+ "JOIN\r\n"
			+ "    utente_colloquio uc ON uc.id_utente = :idU \r\n"
			+ "WHERE\r\n"
			+ "    c.id_candidatura = :idC ;\r\n"
			,nativeQuery = true)
	List<Colloquio> findAllColloquiUtenteByCandidatura(@Param("idC") long idC,@Param("idU") long idU);
}
