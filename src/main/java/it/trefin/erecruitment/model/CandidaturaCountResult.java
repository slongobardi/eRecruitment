package it.trefin.erecruitment.model;

import java.time.LocalDate;

public interface CandidaturaCountResult {
    int getCount();
    String getNome();
    LocalDate getDataIscrizione();
    Long getIdAzienda();
}