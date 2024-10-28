package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trefin.erecruitment.model.ConfirmToken;
import it.trefin.erecruitment.model.Utente;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {
	ConfirmToken findByToken(String token);

	void deleteByUserId(long id);
}
