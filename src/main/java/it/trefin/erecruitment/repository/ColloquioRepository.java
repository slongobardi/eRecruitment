package it.trefin.erecruitment.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

	@Query(value = "SELECT COUNT(colloquio.id),SUM(colloquio.feedback =0),SUM(colloquio.feedback = 1)" + "FROM "
			+ "colloquio,candidatura " + "WHERE "
			+ "colloquio.id_candidatura = id_candidatura && id_candidatura = candidatura.id && candidatura.id_azienda = :id && "
			+ "colloquio.ultimo_colloquio BETWEEN :start AND :end  ;", nativeQuery = true)
	Object[] totalFeedback(@Param("id")int  id,@Param("start") Date startDate,@Param("end") Date endDate);

}
