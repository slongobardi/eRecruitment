package it.trefin.erecruitment.service;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.trefin.erecruitment.dto.ColloquioDto;
import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.mapper.ColloquioMapper;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.mapper.UtenteMapper;
import it.trefin.erecruitment.mapper.UtenteTitoliStudioMapper;
import it.trefin.erecruitment.model.Candidatura;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Esito;
import it.trefin.erecruitment.model.Questionario;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Stato;
import it.trefin.erecruitment.model.Studi;
import it.trefin.erecruitment.model.TitoliStudio;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteCandidatura;
import it.trefin.erecruitment.model.UtenteTitoliStudio;
import it.trefin.erecruitment.repository.CandidaturaRepository;
import it.trefin.erecruitment.repository.ColloquioRepository;
import it.trefin.erecruitment.repository.ConfirmTokenRepository;
import it.trefin.erecruitment.repository.QuestionarioRepository;
import it.trefin.erecruitment.repository.SkillRepository;
import it.trefin.erecruitment.repository.TitoliStudioRepository;
import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;
import it.trefin.erecruitment.repository.UtenteRepository;
import it.trefin.erecruitment.repository.UtenteTitoliStudioRepository;

@Service
public class UtenteService {
	private Logger logger = LoggerFactory.getLogger(UtenteService.class);
	@Autowired
	private UtenteRepository uRepository;
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private UtenteCandidaturaService utenteCandidaturaService;
	
	@Autowired
	private UtenteTitoliStudioRepository utenteTitoliStudioRepository;
	
	@Autowired
	private TitoliStudioRepository titoliStudioRepository;
	
	@Autowired
	private ColloquioRepository colloquioRepository;
	
	@Autowired
	private QuestionarioRepository questionarioRepository;
	
	@Autowired
	private ColloquioRepository cRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private EmailService eService;
	@Autowired
	private ConfirmTokenRepository confirmTokenRepository;
	
	@Autowired
	private UtenteCandidaturaRepository utenteCandidaturaRepository;

	public Response<UtenteDto, Status> visualizzaUtente(long id) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente utente = uRepository.findById(id).orElse(null);
			if (utente != null) {
				UtenteDto dto = UtenteMapper.toDto(utente);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun Utente trovato con l'ID fornito");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaUtente in errore: " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> aggiornaUtente(Utente utente, Long id) {
	    Response<UtenteDto, Status> response = new Response<>();
	    try {
	        Utente u = uRepository.findById(id).orElse(null);
	        if (u != null) {
	        	u.setCellulare(utente.getCellulare());
	        	u.setCitta(utente.getCitta());
	        	u.setEmail(utente.getEmail());
	        	u.setIndirizzo(utente.getIndirizzo());
	        	u.setCategoriaProtetta(utente.isCategoriaProtetta());
	        	u.setDataNascita(utente.getDataNascita());
	        	u.setCodiceFiscale(utente.getCodiceFiscale());
	        	u.setTelefono(utente.getTelefono());
	        	u.setGenere(utente.getGenere());
	        	u.setOrigine(utente.getOrigine());
	        	u.setComuneNascita(utente.getComuneNascita());
	        	u.setPercentualeInvalidita(utente.getPercentualeInvalidita());

	            
	            if (utente.isCategoriaProtetta()) {
	                u.setPercentualeInvalidita(utente.getPercentualeInvalidita());
	            } else {
	                u.setPercentualeInvalidita(null); 
	            }

	            uRepository.save(u);
	            response.setData(UtenteMapper.toDto(u));
	            response.setStatus(Status.OK);
	            response.setDescrizione("Utente modificato con successo.");
	        } else {
	            response.setStatus(Status.SYSTEM_ERROR);
	            response.setDescrizione("Utente non trovato.");
	        }
	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
	    }
	    return response;
	}


	public Response<UtenteDto, Status> aggiornaDescrizioneUtente(Utente utente, Long id) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null) {
				u.setDescrizione(utente.getDescrizione());
				uRepository.save(u);
				response.setData(UtenteMapper.toDto(u));
				response.setStatus(Status.OK);
				response.setDescrizione("Utente modificato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaDescrizioneUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> eliminaSkill(Set<Skill> listaSkill, Long id) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null) {
				u.setListaSkill(listaSkill);
				uRepository.save(u);
				response.setData(UtenteMapper.toDto(u));
				response.setStatus(Status.OK);
				response.setDescrizione("Utente modificato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("modificaSkill in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}
	
	@Transactional
	public Response<UtenteDto, Status> eliminaUtente(long id) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null) {
				confirmTokenRepository.deleteByUserId(u.getId());
				response.setData(UtenteMapper.toDto(u));
				uRepository.delete(u);
				response.setStatus(Status.OK);
				response.setDescrizione("Utente eliminato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<List<UtenteDto>, Status> visualizzaTuttiUtenti() {
		Response<List<UtenteDto>, Status> response = new Response<>();
		try {
			response.setData(uRepository.findAll().stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Utenti ritornati con successo.");
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenti in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<List<SkillDto>, Status> skillUtente(long id) {
		Response<List<SkillDto>, Status> response = new Response<>();
		try {
			List<Skill> listaSkillUtente = new ArrayList<>(this.uRepository.findById(id).orElse(null).getListaSkill());
			response.setData(listaSkillUtente.stream().map(SkillMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("SkillUtente con successo.");
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("skillUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<List<UtenteTitoliStudioDto>, Status> titoliUtente(long id) {
		Response<List<UtenteTitoliStudioDto>, Status> response = new Response<>();
		try {
			List<UtenteTitoliStudio> listaTitoliUtente = new ArrayList<>(
					this.uRepository.findById(id).orElse(null).getUtenteTitoliStudio());
			response.setData(
					listaTitoliUtente.stream().map(UtenteTitoliStudioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("TitoliUtente con successo.");
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("titoliUtente in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<List<UtenteDto>, Status> getAllNotUser() {
		Response<List<UtenteDto>, Status> response = new Response<>();
		try {
			List<Utente> responseQuery = uRepository.findAllNotUser();
			response.setData(responseQuery.stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("ok");
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("getAllNotUser in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}
	
	public Response<List<UtenteDto>, Status> getAllNormalUser(long idAzienda) {
	    Response<List<UtenteDto>, Status> response = new Response<>();

	    try {
	        List<Utente> utenti = uRepository.findAllNormalUser(idAzienda);

	        List<UtenteDto> data = utenti.stream().map(u -> {
	            UtenteDto dto = new UtenteDto();

	            dto.setId(u.getId());
	            dto.setNome(u.getNome());
	            dto.setCognome(u.getCognome());
	            dto.setEmail(u.getEmail());
	            dto.setCellulare(u.getCellulare());
	            dto.setDataNascita(u.getDataNascita());
	            dto.setStato(u.getStato());

	            // id azienda: meglio prenderlo dalla relation
	            dto.setIdAzienda(
	                u.getAzienda() != null ? u.getAzienda().getId() : idAzienda
	            );

	            return dto;
	        }).collect(Collectors.toList());

	        response.setData(data);
	        response.setStatus(Status.OK);
	        response.setDescrizione("ok");

	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("getAllNormalUser in errore " + e.getMessage());
	        logger.warn(e.getMessage());
	    }

	    return response;
	}

	
	public Response<List<UtenteDto>, Status> findUtentePerColloquioAzienda(long idAzienda) {
		Response<List<UtenteDto>, Status> response = new Response<>();
		try {
			List<Utente> responseQuery = uRepository.findUtentePerAzienda(idAzienda);
			response.setData(responseQuery.stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("ok");
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("findUtentePerColloquioAzienda in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}
	
	

	public Response<Utente, Status> modificaColloquio(Long idCandidato, Colloquio c, SimpleMailMessage s) {
		Response<Utente, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(idCandidato).orElse(null);
			if (u != null) {
				c.setUtente(u);
				cRepository.save(c);
				

				eService.inviaEmail(s.getTo(), s.getSubject(), s.getText());

				response.setStatus(Status.OK);
				response.setDescrizione("Colloquio modificato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("modificaColloquio in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}
	
	public Response<Utente, Status> modificaColloquioPhone(Long idCandidato, Colloquio c) {
		Response<Utente, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(idCandidato).orElse(null);
			if (u != null) {
				c.setUtente(u);

				cRepository.save(c);

				response.setStatus(Status.OK);
				response.setDescrizione("Colloquio modificato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("modificaColloquio in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response; 
	}

	public Response<UtenteDto, Status> aggiornaCV(long id, MultipartFile cv) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null && !cv.isEmpty()) {
				Date date = new Date(System.currentTimeMillis());
				u.setDataModificaCv(date);
				u.setCv(cv.getBytes());
				uRepository.save(u);
				UtenteDto dto = UtenteMapper.toDto(u);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("CV Utente salvato con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("CV è null o Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaCV in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> aggiungiSkill(Long id, Long idSkill) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Skill skill=skillRepository.findById(idSkill).get();
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null) {
				if (u.getListaSkill().contains(skill)) {
					response.setStatus(Status.SYSTEM_ERROR);
					response.setDescrizione("La skill è già presente nella lista.");
				} else {
					u.getListaSkill().add(skill);
					uRepository.save(u);
					response.setData(UtenteMapper.toDto(u));
					response.setStatus(Status.OK);
					response.setDescrizione("Skill aggiunta con successo.");
				}
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiungiSkill in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> aggiornaFoto(long id, MultipartFile foto) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null && !foto.isEmpty()) {
				u.setFoto(foto.getBytes());
				uRepository.save(u);
				UtenteDto dto = UtenteMapper.toDto(u);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Foto Utente salvata con successo.");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Foto è null o Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaFoto in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> byEmail(String email) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente utente = uRepository.findByEmail(email);
			if (utente != null) {
				response.setData(UtenteMapper.toDto(utente));
				response.setStatus(Status.OK);
				response.setDescrizione("Utente trovato");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("byEmail in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<UtenteDto, Status> complete(Long id) {
		Response<UtenteDto, Status> response = new Response<>();
		try {
			Utente u = uRepository.findById(id).orElse(null);
			if (u != null) {
				u.setCompleted(true);
				u.setDataIscrizione(new Date(System.currentTimeMillis()));
				uRepository.save(u);
				response.setData(UtenteMapper.toDto(u));
				response.setStatus(Status.OK);
				response.setDescrizione("Utente completato");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Utente non trovato.");
			}
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("complete in errore " + e.getMessage());
			logger.warn(e.getMessage());
		}
		return response;
	}

	public Response<List<ColloquioDto>, Status> getColloqui(long id) {
		Response<List<ColloquioDto>, Status> response = new Response<>();
		
		try {
			Utente u = uRepository.findById(id).get();
			
			response.setData(u.getListaColloquii().stream().map(ColloquioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("fatto");
			
		}catch(Exception e) {
			response.setStatus(Status.KO);
			response.setDescrizione(e.getMessage());
			logger.warn(e.getMessage());
		}
		
		return response;
	}

	public Response<Questionario, Status> addQuestionario(Long idU, Long idC, Questionario questionario) {
	    Response<Questionario, Status> response = new Response<>();

	    try {
	        Optional<Questionario> existing = questionarioRepository.findByUtenteIdAndCandidaturaId(idU, idC);
	        if (existing.isPresent()) {
	            response.setStatus(Status.ALREADY_EXISTS);
	            response.setDescrizione("Hai già compilato il questionario per questa candidatura.");
	            return response;
	        }

	        Utente u = uRepository.findById(idU).orElseThrow(() -> new RuntimeException("Utente non trovato"));
	        Candidatura candidatura = candidaturaRepository.findById(idC).orElseThrow(() -> new RuntimeException("Candidatura non trovata"));

	        questionario.setUtente(u);
	        questionario.setCandidatura(candidatura);
	        Questionario savedQuestionario = questionarioRepository.save(questionario);

	        u.setTrasferimento(questionario.getTrasferimento());
	        uRepository.save(u);

	        Colloquio colloquio = new Colloquio();
	        colloquio.setUtente(u);
	        colloquio.setCandidatura(candidatura);
	        colloquio.setDataColloquio(candidatura.getPubblicazione());
	        colloquio.setEsito(Esito.InValutazione);
	        colloquio.setTipo("conoscitivo-tecnico");
	        colloquioRepository.save(colloquio);

	        UtenteTitoliStudio utenteTitoliStudio = new UtenteTitoliStudio();
	        utenteTitoliStudio.setUtente(u);
	        utenteTitoliStudio.setDescrizione(questionario.getIndirizzoStudio());
	        utenteTitoliStudio.setTitoliStudio(titoliStudioRepository.findByStudi(Studi.DIPLOMA));
	        utenteTitoliStudio.setCompleted(false);
	        utenteTitoliStudioRepository.save(utenteTitoliStudio);

	        UtenteCandidatura utenteCandidatura = new UtenteCandidatura();
	        utenteCandidatura.setUtente(u);
	        utenteCandidatura.setCandidatura(candidatura);
	        utenteCandidatura.setDataIscrizione(new java.sql.Date(System.currentTimeMillis()));
	        utenteCandidatura.setStato(Stato.ATTESA);
	        utenteCandidatura.setDisabilitato(false);
	        utenteCandidaturaService.inserisciUtenteCandidatura(
	            utenteCandidatura,
	            candidatura.getAzienda().getId(),
	            u.getId()
	        );

	        List<Skill> skills = skillRepository.findAll();
	        String altroSkill = questionario.getAltroSkill();
	        if (altroSkill != null && !altroSkill.trim().isEmpty()) {
	            Skill skill = skills.stream()
	                .filter(s -> s.getNome().replace(" ", "").equalsIgnoreCase(altroSkill.replace(" ", "")))
	                .findFirst()
	                .orElseGet(() -> {
	                    Skill nuova = new Skill();
	                    nuova.setNome(altroSkill);
	                    return skillRepository.save(nuova);
	                });
	            aggiungiSkill(skill.getId(), idU);
	        }

	        String altroSoftware = questionario.getAltroSoftware();
	        if (altroSoftware != null && !altroSoftware.trim().isEmpty()) {
	            Skill software = skills.stream()
	                .filter(s -> s.getNome().replace(" ", "").equalsIgnoreCase(altroSoftware.replace(" ", "")))
	                .findFirst()
	                .orElseGet(() -> {
	                    Skill nuova = new Skill();
	                    nuova.setNome(altroSoftware);
	                    return skillRepository.save(nuova);
	                });
	            aggiungiSkill(software.getId(), idU);
	        }

	        String[] skillFisse = {
	            "C", "C++", "Autocad", "Catia", "Rhinoceros",
	            "SolidWorks", "HTML", "CSS3", "OpenOffice", "Python", "SQL"
	        };

	        for (String nomeSkill : skillFisse) {
	            Skill skill = skillRepository.findByNome(nomeSkill);
	            if (skill == null) {
	                skill = new Skill();
	                skill.setNome(nomeSkill);
	                skill = skillRepository.save(skill);
	            }
	            aggiungiSkill(idU, skill.getId());
	        }

	        response.setData(savedQuestionario);
	        response.setStatus(Status.OK);
	        response.setDescrizione("Questionario salvato con successo.");

	    } catch (Exception e) {
	        response.setStatus(Status.KO);
	        response.setDescrizione("Errore: " + e.getMessage());
	        logger.warn("Errore durante il salvataggio del questionario: " + e.getMessage(), e);
	    }

	    return response;
	}



		  

	public Response<Questionario, Status> getQuestionario(Long idU, Long idE) {
	    Response<Questionario, Status> response = new Response<>();

	    try {
	        Optional<Questionario> questionarioOpt = questionarioRepository.findByUtenteIdAndCandidaturaId(idU, idE);
	        
	        if (questionarioOpt.isPresent()) {
	            response.setData(questionarioOpt.get());
	            response.setStatus(Status.OK);
	            response.setDescrizione("Questionario trovato.");
	        } else {
	            response.setStatus(Status.KO); 
	            response.setDescrizione("Nessun questionario trovato per questo utente e candidatura.");
	        }
	        
	    } catch (Exception e) {
	        response.setStatus(Status.KO);
	        response.setDescrizione("Errore: " + e.getMessage());
	        logger.warn(e.getMessage(), e);
	    }

	    return response;
	}

	public Response<String, Status> aggiungiNota(Long id, String nota) {
	    Response<String, Status> response = new Response<>();
	    
	    try {
	        Utente u = uRepository.findById(id).orElse(null);

	        if (u != null) {
	            u.setNota(nota);

	            uRepository.save(u);
	            
	            response.setData("Nota aggiornata con successo.");
	            response.setStatus(Status.OK);
	        } else {
	            response.setStatus(Status.SYSTEM_ERROR);
	            response.setDescrizione("Utente non trovato.");
	        }
	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("Errore nell'aggiunta della nota: " + e.getMessage());
	        logger.warn(e.getMessage()); 
	    }

	    return response;
	}
	
	public Response<String, Status> aggiungiData(Long id, Date dataInizio, Date dataFine) {
	    Response<String, Status> response = new Response<>();

	    try {
	        Utente u = uRepository.findById(id).orElse(null);

	        if (u != null) {
	            u.setDataInizio(dataInizio);

	            if (dataFine != null) {
	                u.setDataFine(dataFine);
	            }

	            uRepository.save(u);

	            response.setData("Date aggiornate con successo.");
	            response.setStatus(Status.OK);
	        } else {
	            response.setStatus(Status.SYSTEM_ERROR);
	            response.setDescrizione("Utente non trovato.");
	        }
	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("Errore nell'aggiornamento delle date: " + e.getMessage());
	        logger.warn(e.getMessage());
	    }

	    return response;
	}
	
	public Response<String, Status> getNota(Long id) {
	    Response<String, Status> response = new Response<>();

	    try {
	        Utente u = uRepository.findById(id).orElse(null);

	        if (u != null) {
	            response.setData(u.getNota());
	            response.setStatus(Status.OK);
	        } else {
	            response.setStatus(Status.SYSTEM_ERROR);
	            response.setDescrizione("Utente non trovato.");
	        }
	    } catch (Exception e) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("Errore nel recupero della nota: " + e.getMessage());
	        logger.warn(e.getMessage());
	    }

	    return response;
	}
 
	
	public Response<Map<String, Date>, Status> getData(Long id) {
	    Response<Map<String, Date>, Status> response = new Response<>();

	    Utente u = uRepository.findById(id).orElse(null);
	    if (u == null) {
	        response.setStatus(Status.SYSTEM_ERROR);
	        response.setDescrizione("Utente non trovato.");
	        return response;
	    }

	    Map<String, Date> dateMap = Map.of(
	        "dataInizio", u.getDataInizio(),
	        "dataFine", u.getDataFine()
	    );

	    response.setData(dateMap);
	    response.setStatus(Status.OK);
	    return response;
	}




}
