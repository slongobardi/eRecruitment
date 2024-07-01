package it.trefin.erecruitment.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.trefin.erecruitment.dto.TitoliStudioDto;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.UtenteTitoliStudio;

public class TitoliStudioMapper {
	
	public static TitoliStudioDto toDto(TitoliStudio t) {
		TitoliStudioDto tDto = new TitoliStudioDto();
		
		tDto.setId(t.getId());
		tDto.setStudi(t.getStudi());
		tDto.setTitoliStudio(t.getTitoliStudio().stream().map(UtenteTitoliStudio::getId).collect(Collectors.toList()));
		tDto.setListaCandidature(t.getListaCandidature().stream().map(Candidatura::getId).collect(Collectors.toSet()));
		
		return tDto;
	}
	
	public static TitoliStudio toEntity(TitoliStudioDto tDto,List<UtenteTitoliStudio>listaTitoliStudio,Set<Candidatura>listaCandidature) {
		TitoliStudio t = new TitoliStudio();
		t.setId(tDto.getId());
		t.setStudi(tDto.getStudi());
		t.setTitoliStudio(listaTitoliStudio);
		t.setListaCandidature(listaCandidature);
		
		
		return t;
	}

}
