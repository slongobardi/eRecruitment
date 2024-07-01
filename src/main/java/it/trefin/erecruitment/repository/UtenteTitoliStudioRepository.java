package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.UtenteTitoliStudio;

@Repository
public interface UtenteTitoliStudioRepository extends JpaRepository<UtenteTitoliStudio, Long>{

}
