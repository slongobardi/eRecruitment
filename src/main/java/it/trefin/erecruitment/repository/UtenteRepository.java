package it.trefin.erecruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	Utente findByEmail(String email);

	@Query(value = "SELECT * FROM Utente u WHERE u.ruolo != 3",nativeQuery = true)
	List<Utente> findAllNotUser();


}
