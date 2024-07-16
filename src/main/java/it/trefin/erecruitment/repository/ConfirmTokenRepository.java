package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trefin.erecruitment.model.ConfirmToken;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {
	ConfirmToken findByToken(String token);
}
