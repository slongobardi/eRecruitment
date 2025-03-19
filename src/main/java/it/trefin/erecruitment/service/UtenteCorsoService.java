package it.trefin.erecruitment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.UtenteCorsoDto;
import it.trefin.erecruitment.mapper.UtenteCorsoMapper;
import it.trefin.erecruitment.model.Corso;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.UtenteCorso;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.repository.CorsoRepository;
import it.trefin.erecruitment.repository.UtenteCorsoRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class UtenteCorsoService {
    private Logger logger = LoggerFactory.getLogger(UtenteCorsoService.class);

    @Autowired
    private UtenteCorsoRepository ucRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Response<UtenteCorso, Status> iscriviUtenteACorso(long utenteId, long corsoId) {
        Response<UtenteCorso, Status> response = new Response<>();

        try {
            Optional<UtenteCorso> uc = ucRepository.findByUtenteIdAndCorsoId(utenteId, corsoId);

            if (uc.isPresent()) {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("L'utente è già iscritto a questo corso.");
            } else {
                Corso corso = corsoRepository.findById(corsoId)
                        .orElseThrow(() -> new RuntimeException("Corso non trovato"));
                Utente utente = utenteRepository.findById(utenteId)
                        .orElseThrow(() -> new RuntimeException("Utente non trovato"));

                UtenteCorso utenteCorso = new UtenteCorso();
                utenteCorso.setUtente(utente);
                utenteCorso.setCorso(corso);

                ucRepository.save(utenteCorso);

                utente.setStato("iscritto");
                utenteRepository.save(utente);

                response.setData(utenteCorso);
                response.setStatus(Status.OK);
                response.setDescrizione("Iscrizione al corso effettuata con successo.");
            }
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'iscrizione al corso: " + e.getMessage());
            logger.warn(e.getMessage());
        }
        return response;
    }

    public List<UtenteCorsoDto> visualizzaUtenteCorso(long utenteId) {
        try {
            List<UtenteCorso> utenteCorsi = ucRepository.findByUtenteId(utenteId);
            return utenteCorsi.stream()
                .map(UtenteCorsoMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.warn("Errore nel recupero dei corsi per l'utente con ID: " + utenteId, e);
            return List.of();
        }
    }

    public Response<List<UtenteCorsoDto>, Status> eliminaIscrizioneUtente(long utenteId, long corsoId) {
        Response<List<UtenteCorsoDto>, Status> response = new Response<>();

        try {
            Optional<UtenteCorso> utenteCorso = ucRepository.findByUtenteIdAndCorsoId(utenteId, corsoId);

            if (utenteCorso.isPresent()) {
                ucRepository.delete(utenteCorso.get());
                List<UtenteCorsoDto> updatedList = ucRepository.findAll().stream()
                        .map(UtenteCorsoMapper::toDTO)
                        .collect(Collectors.toList());
                response.setData(updatedList);
                response.setStatus(Status.OK);
                response.setDescrizione("Iscrizione eliminata con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Iscrizione utente al corso non trovata.");
            }
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore nell'eliminazione dell'iscrizione: " + e.getMessage());
            logger.warn(e.getMessage());
        }

        return response;
    }


    
    public List<UtenteCorsoDto> getUtentiIscrittiACorso(Long corsoId) {
        List<UtenteCorso> utentiCorsi = ucRepository.findByCorsoId(corsoId);
        return utentiCorsi.stream()
                          .map(utenteCorso -> new UtenteCorsoDto(utenteCorso))
                          .collect(Collectors.toList());
    }

    
    public boolean isUtenteIscrittoACorso(Long utenteId, Long corsoId) {
        return ucRepository.existsByUtenteIdAndCorsoId(utenteId, corsoId);
    }

}
