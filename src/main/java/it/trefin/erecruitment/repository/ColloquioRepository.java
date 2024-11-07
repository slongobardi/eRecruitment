package it.trefin.erecruitment.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    ArrayList<Colloquio> findByDataColloquioBetween(Date start, Date end);

    @Query(value = "SELECT " + 
            "    COUNT(colloquio.id) AS numeroColloqui, " + 
            "    SUM(colloquio.feedback = 0) AS positivi, " + 
            "    SUM(colloquio.feedback = 1) AS negativi, " + 
            "    sc.perso, " + 
            "    sc.ingaggiato " + 
            "FROM colloquio " + 
            "JOIN candidatura ON colloquio.id_candidatura = candidatura.id " + 
            "LEFT JOIN ( " + 
            "    SELECT " + 
            "        azienda_id, " + 
            "        SUM(ingaggiato = 1) AS ingaggiato, " + 
            "        SUM(perso = 1) AS perso " + 
            "    FROM schedacandidato " + 
            "    GROUP BY azienda_id " + 
            ") AS sc ON sc.azienda_id = candidatura.id_azienda " + 
            "WHERE candidatura.id_azienda = :id " + 
            "AND colloquio.data_colloquio BETWEEN :start AND :end", 
           nativeQuery = true)
    Object[] totalFeedback(@Param("id") long id, @Param("start") Date startDate, @Param("end") Date endDate);


    @Query(value = "SELECT DISTINCT " +
            "    utente.id, " +
            "    utente.nome, " +
            "    utente.cognome, " +
            "    colloquio.feedback, " +
            "    colloquio.descrizione, " +
            "    colloquio.tipo, " +
            "    schedacandidato.ingaggiato, " +
            "    colloquio.data_colloquio " +
            "FROM utente " +
            "JOIN colloquio ON utente.id = colloquio.id_utente " +
            "JOIN candidatura ON colloquio.id_candidatura = candidatura.id " +
            "JOIN schedacandidato ON schedacandidato.utente_id = utente.id " +
            "WHERE candidatura.id_azienda = :id " +
            "AND colloquio.data_colloquio BETWEEN :start AND :end", 
    nativeQuery = true)
Set<Object> reportCandidati(@Param("id") long id, @Param("start") Date startDate, @Param("end") Date endDate);
	
	

	List<Colloquio> findByUtenteId(long idUtente);

	List<Colloquio> findAllByUtenteIdAndCandidaturaId(long idU, long idC);
}