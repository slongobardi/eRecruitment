package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{

	List<Candidatura> findAllByAziendaId(long id_azienda);

	@Query(value="SELECT \r\n"
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
			+ "    utente_candidatura ON candidatura.id_azienda = :idAzienda \r\n"
			+ "WHERE \r\n"
			+ "    utente_candidatura.id_utente = :idUtente ;",nativeQuery=true)
	Object[] findByUtente(@Param("idUtente") long idUtente,@Param("idAzienda") long idAzienda);
}
