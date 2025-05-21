package it.trefin.erecruitment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long>{

	Optional<Questionario> findByUtenteIdAndCandidaturaId(Long idUtente, Long idCandidatura);

}
