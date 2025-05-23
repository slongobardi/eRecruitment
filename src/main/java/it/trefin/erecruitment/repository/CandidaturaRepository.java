package it.trefin.erecruitment.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.CandidaturaCountResult;
import it.trefin.erecruitment.model.Skill;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{

	List<Candidatura> findAllByAziendaId(long id_azienda);

	@Query(value="SELECT DISTINCT \r\n"
			+ "    candidatura.descrizione, \r\n"
			+ "    candidatura.nome, \r\n"
			+ "    candidatura.numero_candidati, \r\n"
			+ "    candidatura.pubblicazione,\r\n"
			+ "    candidatura.id,\r\n"
			+ "    candidatura.ral,\r\n"
			+ "    candidatura.settore,\r\n"
			+ "    candidatura.id_azienda\r\n"
			+ "FROM \r\n"
			+ "    candidatura\r\n"
			+ "JOIN \r\n"
			+ "    utente_candidatura ON candidatura.id=utente_candidatura.id_candidatura  \r\n"
			+ "WHERE \r\n"
			+ " utente_candidatura.id_utente = :idUtente AND "
			+" 	candidatura.id_azienda=:idAzienda ;",nativeQuery=true)
	Object[] findByUtente(@Param("idUtente") long idUtente,@Param("idAzienda") long idAzienda);


	 List<Candidatura> findByIsEventoTrueAndAziendaId(long id_azienda);
	 
	 
	 @Query(value = "SELECT COUNT(*) as count, c.nome, uc.data_iscrizione, c.id_azienda AS idAzienda \r\n"
			 + "FROM candidatura c \r\n"
			 + "   JOIN utente_candidatura uc ON uc.id_candidatura = c.id \r\n"
		     + "   WHERE uc.data_iscrizione = :dataIscrizione \r\n"
		     + "   GROUP BY c.nome, c.id_azienda, uc.data_iscrizione", nativeQuery = true)
		    List<CandidaturaCountResult> countCandidatureByDate(
		        @Param("dataIscrizione") LocalDate dataIscrizione
		    );

		   
}


