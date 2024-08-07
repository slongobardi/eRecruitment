package it.trefin.erecruitment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.model.Skill;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

	List<Skill> findAllByUtenteId(long id);
}
