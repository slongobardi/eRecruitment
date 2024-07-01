package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.AmministratoreDto;
import it.trefin.erecruitment.model.Amministratore;
import it.trefin.erecruitment.model.Azienda;

public class AmministratoreMapper {
	
	public static AmministratoreDto toDto(Amministratore a) {
		AmministratoreDto aDto = new AmministratoreDto();
		aDto.setCellulare(a.getCellulare());
		aDto.setCognome(a.getCognome());
		aDto.setDataNascita(a.getDataNascita());
		aDto.setEmail(a.getEmail());
		aDto.setFoto(a.getFoto());
		aDto.setId(a.getId());
		aDto.setNome(a.getNome());
		aDto.setPassword(a.getPassword());
		aDto.setAzienda(a.getAzienda().getId());
		aDto.setSuperAdmin(a.isSuperAdmin());
		return aDto;
	}
	
	public static Amministratore toEntity(AmministratoreDto aDto,Azienda azienda) {
		Amministratore a = new Amministratore();
		a.setCellulare(aDto.getCellulare());
		a.setCognome(aDto.getCognome());
		a.setDataNascita(aDto.getDataNascita());
		a.setEmail(aDto.getEmail());
		a.setFoto(aDto.getFoto());
		a.setId(aDto.getId());
		a.setNome(aDto.getNome());
		a.setPassword(aDto.getPassword());
		a.setAzienda(azienda);
		a.setSuperAdmin(aDto.isSuperAdmin());
	
		return a;
	}
	

}
