package it.trefin.erecruitment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.CorsoDto;
import it.trefin.erecruitment.dto.UtenteCorsoDto;
import it.trefin.erecruitment.mapper.CorsoMapper;
import it.trefin.erecruitment.mapper.UtenteCorsoMapper;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Corso;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCorso;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.CorsoRepository;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteCorsoRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class CorsoService {
    private Logger logger = LoggerFactory.getLogger(CorsoService.class);

    @Autowired
    private CorsoRepository corsoRepository;
    
    @Autowired
    private CandidaturaRepository candidaturaRepository;
    
    @Autowired
    private UtenteCandidaturaRepository utenteCandidaturaRepository;

    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private UtenteCorsoRepository utenteCorsoRepository;

    public Response<CorsoDto, Status> inserisciCorso(CorsoDto corsoDto) {
        Response<CorsoDto, Status> response = new Response<>();

        try {
            Candidatura candidatura = candidaturaRepository.findById(corsoDto.getCandidaturaId()).orElse(null);

            if (candidatura != null) {
                Corso corso = CorsoMapper.toEntity(corsoDto);
                corso.setCandidatura(candidatura);
                corsoRepository.save(corso);
                response.setData(CorsoMapper.toDTO(corso, false)); 
                response.setStatus(Status.OK);
                response.setDescrizione("Corso inserito con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: Candidatura non trovata.");
            }

            return response;
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'inserimento del corso: " + e.getMessage());
            logger.warn(e.getMessage());
            return response;
        }
    }

    public Response<CorsoDto, Status> visualizzaCorso(long id) {
        Response<CorsoDto, Status> response = new Response<>();
        
        try {
            Corso corso = corsoRepository.findById(id).orElse(null);
            if (corso != null) {
                response.setData(CorsoMapper.toDTO(corso, true));
                response.setStatus(Status.OK);
                response.setDescrizione("Corso trovato con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: Corso non trovato.");
            }
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante il recupero del corso: " + e.getMessage());
            logger.warn(e.getMessage());
        }
        
        return response;
    }
    
    public Response<List<CorsoDto>, Status> visualizzaCorsiByAziendaId(long aziendaId) {
        Response<List<CorsoDto>, Status> response = new Response<>();

        try {
            List<Corso> corsi = corsoRepository.findByCandidatura_AziendaId(aziendaId);
            
            if (corsi.isEmpty()) {
                response.setStatus(Status.FAIL);
                response.setDescrizione("Nessun corso trovato per l'azienda specificata.");
            } else {
                response.setData(corsi.stream().map(corso -> CorsoMapper.toDTO(corso, true)).collect(Collectors.toList()));
                response.setStatus(Status.OK);
                response.setDescrizione("Corsi trovati con successo per l'azienda.");
            }
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante il recupero dei corsi: " + e.getMessage());
            logger.warn(e.getMessage());
        }

        return response;
    }


    public Response<CorsoDto, Status> aggiornaCorso(CorsoDto corsoDto) {
        Response<CorsoDto, Status> response = new Response<>();
        Optional<Corso> corsoOpt = corsoRepository.findById(corsoDto.getId());

        if (corsoOpt.isEmpty()) {
            response.setStatus(Status.FAIL);
            response.setDescrizione("Errore: Corso non trovato.");
            return response;
        }

        Corso corso = corsoOpt.get();
        
        corso.setNome(corsoDto.getNome()); 
        corso.setSede(corsoDto.getSede());
        corso.setSettore(corsoDto.getSettore());
        corso.setDescrizione(corsoDto.getDescrizione());

        corsoRepository.save(corso);
        response.setStatus(Status.OK);
        response.setDescrizione("Corso aggiornato con successo.");
        response.setData(CorsoMapper.toDTO(corso, true));  
        
        return response;
    }

    public Response<CorsoDto, Status> eliminaCorso(long id) {
        Response<CorsoDto, Status> response = new Response<>();
        
        try {
            Corso corso = corsoRepository.findById(id).orElse(null);
            if (corso != null) {
                corsoRepository.delete(corso);
                response.setData(CorsoMapper.toDTO(corso, true));  
                response.setStatus(Status.OK);
                response.setDescrizione("Corso eliminato con successo.");
            } else {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: Corso non trovato.");
            }
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'eliminazione del corso: " + e.getMessage());
            logger.warn(e.getMessage());
        }
        
        return response;
    }

    public Response<List<CorsoDto>, Status> visualizzaTuttiCorsi() {
        Response<List<CorsoDto>, Status> response = new Response<>();
        
        try {
            List<Corso> corsi = corsoRepository.findAll();
            response.setData(corsi.stream().map(corso -> CorsoMapper.toDTO(corso, true)).collect(Collectors.toList()));
            response.setStatus(Status.OK);
            response.setDescrizione("Tutti i corsi ritornati con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante il recupero dei corsi: " + e.getMessage());
            logger.warn(e.getMessage());
        }
        
        return response;
    }

    public Response<UtenteCorsoDto, Status> aggiungiAlCorso(long utenteId, long corsoId) {
        Response<UtenteCorsoDto, Status> response = new Response<>();

        try {
            Optional<Corso> corso = corsoRepository.findById(corsoId);
            if (!corso.isPresent()) {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: Corso non trovato.");
                return response;
            }

            Optional<Utente> utente = utenteRepository.findById(utenteId);
            if (!utente.isPresent()) {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: Utente non trovato.");
                return response;
            }

            Optional<UtenteCorso> utenteCorso = utenteCorsoRepository.findByUtenteIdAndCorsoId(utenteId, corsoId);
            if (utenteCorso.isPresent()) {
                response.setStatus(Status.SYSTEM_ERROR);
                response.setDescrizione("Errore: L'utente è già iscritto a questo corso.");
                return response;
            }

            UtenteCorso nuovoUtenteCorso = new UtenteCorso();
            nuovoUtenteCorso.setUtente(utente.get());
            nuovoUtenteCorso.setCorso(corso.get());

            utenteCorsoRepository.save(nuovoUtenteCorso);

            response.setData(UtenteCorsoMapper.toDTO(nuovoUtenteCorso));
            response.setStatus(Status.OK);
            response.setDescrizione("Utente aggiunto al corso con successo.");
        } catch (Exception e) {
            response.setStatus(Status.SYSTEM_ERROR);
            response.setDescrizione("Errore durante l'aggiunta dell'utente al corso: " + e.getMessage());
            logger.warn(e.getMessage());
        }

        return response;
    }
}
