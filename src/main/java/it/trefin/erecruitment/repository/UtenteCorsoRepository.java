package it.trefin.erecruitment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.UtenteCorso;

@Repository
public interface UtenteCorsoRepository extends JpaRepository<UtenteCorso, Long> {
	List<UtenteCorso> findByUtenteId(Long utenteId);
	List<UtenteCorso> findByCorsoId(Long corsoId); 
    boolean existsByUtenteIdAndCorsoId(Long utenteId, Long corsoId);
    Optional<UtenteCorso> findByUtenteIdAndCorsoId(Long utenteId, Long corsoId);
	List<UtenteCorso> findAllByUtenteId(Long utenteId);


}
