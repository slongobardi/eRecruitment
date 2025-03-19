package it.trefin.erecruitment.mapper;

import it.trefin.erecruitment.dto.CorsoDto;
import it.trefin.erecruitment.model.Corso;
import it.trefin.erecruitment.model.Candidatura;

public class CorsoMapper {

    public static CorsoDto toDTO(Corso corso, boolean isUpdate) {
        if (corso == null) {
            return null;
        }

        CorsoDto dto = new CorsoDto();
        dto.setId(corso.getId());

        if (isUpdate) {
            dto.setNome(corso.getNome());
            dto.setSede(corso.getSede());
            dto.setSettore(corso.getSettore());
            dto.setDescrizione(corso.getDescrizione());
            dto.setDataInizio(corso.getDataInizio()); 
            dto.setDataFine(corso.getDataFine());     
        } else {
            dto.setNome(corso.getCandidatura() != null ? corso.getCandidatura().getNome() : "");
            dto.setSede(corso.getCandidatura() != null ? corso.getCandidatura().getSede() : "");
            dto.setSettore(corso.getCandidatura() != null ? corso.getCandidatura().getSettore() : "");
            dto.setDescrizione(corso.getCandidatura() != null ? corso.getCandidatura().getDescrizione() : "");
        }

        if (corso.getCandidatura() != null) {
            dto.setCandidaturaId(corso.getCandidatura().getId());
        }

        return dto;
    }

    public static Corso toEntity(CorsoDto dto) {
        if (dto == null) {
            return null;
        }

        Corso corso = new Corso();
        corso.setId(dto.getId());
        corso.setNome(dto.getNome()); 
        corso.setSede(dto.getSede());
        corso.setSettore(dto.getSettore()); 
        corso.setDescrizione(dto.getDescrizione()); 
        corso.setDataInizio(dto.getDataInizio()); 
        corso.setDataFine(dto.getDataFine());     

        return corso;
    }
}
