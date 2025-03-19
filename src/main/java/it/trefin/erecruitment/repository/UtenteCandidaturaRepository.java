package it.trefin.erecruitment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.UtenteCandidatura;

@Repository
public interface UtenteCandidaturaRepository extends JpaRepository<UtenteCandidatura, Long>{

	List<UtenteCandidatura> findAllByUtenteId(long id);
	List<UtenteCandidatura> findAllByCandidaturaId(long id);
	Optional<UtenteCandidatura> findByUtenteIdAndCandidaturaId(Long utenteId, Long candidaturaId);
	ArrayList<UtenteCandidatura> getFindByCandidaturaId(long id_candidatura);
	
	@Query(value="SELECT DISTINCT \r\n"
			+ "    utente_candidatura.id ,"
			+" utente_candidatura.data_iscrizione, "
			+" utente_candidatura.nota, "
			+" utente_candidatura.stato, "
			+ "utente_candidatura.id_candidatura, "
			+ "utente_candidatura.id_utente, "
			+ "utente_candidatura.disabilitato \r\n"
			+ "FROM \r\n"
			+ "    schedacandidato \r\n"
			+ "JOIN \r\n"
			+ "    utente ON schedacandidato.utente_id = utente.id \r\n"
			+" JOIN \r\n"
			+" 		utente_candidatura ON utente_candidatura.id_utente=utente.id "	
			+" WHERE \r\n"
			+ " schedacandidato.picking_list = TRUE AND "
			+" 	schedacandidato.azienda_id=:idAzienda "
			+ "GROUP BY utente_candidatura.id_utente;",nativeQuery=true)
	List<UtenteCandidatura> findPickingList(@Param("idAzienda") long idA);
	@Query("DELETE FROM UtenteCandidatura uc WHERE uc.utente.id = :idUtente AND uc.candidatura.id = :idCandidatura")
	void deleteByUtenteIdAndCandidaturaId(@Param("idUtente") long idUtente, @Param("idCandidatura") long idCandidatura);
}
