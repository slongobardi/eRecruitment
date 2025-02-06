package it.trefin.erecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.model.Studi;
import it.trefin.erecruitment.model.TitoliStudio;

@Repository
public interface TitoliStudioRepository extends JpaRepository<TitoliStudio, Long>{

	TitoliStudio findByStudi(Studi diploma);

}
