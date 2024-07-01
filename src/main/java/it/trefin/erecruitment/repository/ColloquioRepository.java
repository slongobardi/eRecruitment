package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Colloquio;

@Repository
public interface ColloquioRepository extends JpaRepository<Colloquio, Long>{

}
