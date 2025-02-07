package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long>{

	Questionario findByUtenteIdAndCandidaturaId(Long idU, Long idE);

}
