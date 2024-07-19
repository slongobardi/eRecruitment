package it.trefin.erecruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.trefin.erecruitment.dto.SkillDto;
import it.trefin.erecruitment.dto.UtenteDto;
import it.trefin.erecruitment.dto.UtenteTitoliStudioDto;
import it.trefin.erecruitment.mapper.SkillMapper;
import it.trefin.erecruitment.mapper.UtenteMapper;
import it.trefin.erecruitment.mapper.UtenteTitoliStudioMapper;
import it.trefin.erecruitment.model.Colloquio;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Skill;
import it.trefin.erecruitment.model.Utente;
import it.trefin.erecruitment.model.UtenteTitoliStudio;
import it.trefin.erecruitment.repository.ColloquioRepository;
import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository uRepository;
	@Autowired
	private ColloquioRepository cRepository;

	@Autowired
	private EmailService eService;

	public Response<UtenteDto, Status> visualizzaUtente(long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente utente = uRepository.findById(id).get();

			if (utente != null) {
				UtenteDto dto = UtenteMapper.toDto(utente);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessun Utente trovato con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaUtente in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<UtenteDto, Status> aggiornaUtente(Utente utente, Long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente u = uRepository.findById(id).get();

			u.setCellulare(utente.getCellulare());
			u.setCitta(utente.getCitta());
			u.setEmail(utente.getEmail());
			u.setIndirizzo(utente.getIndirizzo());
			u.setDataNascita(utente.getDataNascita());
			uRepository.save(u);
			response.setData(UtenteMapper.toDto(u));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> aggiornaDescrizioneUtente(Utente utente, Long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente u = uRepository.findById(id).get();

			u.setDescrizione(utente.getDescrizione());

			uRepository.save(u);
			response.setData(UtenteMapper.toDto(u));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> modificaSkill(Set<Skill> listaSkill, Long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {

			Utente u = uRepository.findById(id).get();

			u.setListaSkill(listaSkill);

			uRepository.save(u);
			response.setData(UtenteMapper.toDto(u));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente modificato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> eliminaUtente(long id) {

		Response<UtenteDto, Status> response = new Response<>();

		try {
			Utente u = uRepository.findById(id).get();
			response.setData(UtenteMapper.toDto(u));
			uRepository.delete(u);
			response.setStatus(Status.OK);
			response.setDescrizione("Utente eliminato con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaUtente in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<UtenteDto>, Status> visualizzaTuttiUtenti() {

		Response<List<UtenteDto>, Status> response = new Response<>();

		try {

			response.setData(uRepository.findAll().stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente ritornati con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTuttiUtenti in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<SkillDto>, Status> skillUtente(long id) {

		Response<List<SkillDto>, Status> response = new Response<>();

		try {
			List<Skill> listaSkillUtente = new ArrayList<>(this.uRepository.getReferenceById(id).getListaSkill());
			response.setData(listaSkillUtente.stream().map(SkillMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("SkillUtente con successo.");

			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("SkillUtente in errore " + e.getMessage());
			return response;

		}
	}

	public Response<List<UtenteTitoliStudioDto>, Status> titoliUtente(long id) {

		Response<List<UtenteTitoliStudioDto>, Status> response = new Response<>();

		try {
			List<UtenteTitoliStudio> listaSkillUtente = new ArrayList<>(
					this.uRepository.getReferenceById(id).getUtenteTitoliStudio());
			response.setData(
					listaSkillUtente.stream().map(UtenteTitoliStudioMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("titoliUtente con successo.");

			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("titoliUtente in errore " + e.getMessage());
			return response;

		}
	}

	public Response<List<UtenteDto>, Status> getAllNotUser() {
		Response<List<UtenteDto>, Status> response = new Response<>();

		try {
			List<Utente> responseQuery = uRepository.findAllNotUser();
			response.setData(responseQuery.stream().map(UtenteMapper::toDto).collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("ok");

			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("allNotUser in errore " + e.getMessage());
			return response;
		}
	}

	/*
	 * public Response<Utente, Status> modificaColloquio(Long idCandidato, Long
	 * idColloquio) { Response<Utente, Status> response = new Response<>(); try {
	 * Utente u = uRepository.findById(idCandidato).get(); Colloquio colloquio =
	 * cRepository.findById(idColloquio).get();
	 * u.getListaColloquii().add(colloquio); uRepository.save(u);
	 * response.setStatus(Status.OK); response.setDescrizione("ok");
	 * 
	 * return response; } catch (Exception e) {
	 * response.setStatus(Status.SYSTEM_ERROR);
	 * response.setDescrizione("modificaColloquio in errore " + e.getMessage());
	 * return response; }
	 * 
	 * }
	 */
	public Response<Utente, Status> modificaColloquio(Long idCandidato, Colloquio c, SimpleMailMessage s) {
		Response<Utente, Status> response = new Response<>();
		try {

			Utente u = uRepository.findById(idCandidato).get();
			Colloquio col = cRepository.save(c);
			u.getListaColloquii().add(col);
			uRepository.save(u);

			System.out.println(s.getTo()[0]);
			eService.inviaEmail(s.getTo(), s.getSubject(), s.getText());

			response.setStatus(Status.OK);
			response.setDescrizione("ok");

			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("modificaColloquio in errore " + e.getMessage());
			return response;
		}
	}

	public Response<UtenteDto, Status> aggiornaCV(long id, MultipartFile cv) {

		Response<UtenteDto, Status> response = new Response<>();

		try {
			Utente u = uRepository.findById(id).get();
			if (!cv.isEmpty()) {

				u.setCv(cv.getBytes());
				uRepository.save(u);

				UtenteDto dto = UtenteMapper.toDto(u);

				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Cv Utente salvato con successo.");
				return response;

			}

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("Cv è null.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaCV  in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> aggiungiSkill(Long id, Skill s) {
		Response<UtenteDto, Status> response = new Response<>();

		try {
			Utente u = uRepository.findById(id).get();

			if (u.getListaSkill().contains(s)) {
				response.setStatus(Status.SYSTEM_ERROR);
				System.out.println("ciaoo");
				response.setDescrizione("La skill è già presente nella lista.");
			} else {
				u.getListaSkill().add(s);
				uRepository.save(u);

				UtenteDto dto = UtenteMapper.toDto(u);

				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Skill aggiunta con successo.");
			}

			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiungiSkill in errore " + e.getMessage());
			return response;
		}
	}

	public Response<UtenteDto, Status> aggiornaFoto(long id, MultipartFile foto) {

		Response<UtenteDto, Status> response = new Response<>();

		try {
			Utente u = uRepository.findById(id).get();
			if (!foto.isEmpty()) {

				u.setFoto(foto.getBytes());

				uRepository.save(u);
				UtenteDto dto = UtenteMapper.toDto(u);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("Foto Utente salvato con successo.");
				return response;
			} else {

				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Foto è null.");
				return response;

			}

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaFoto  in errore " + e.getMessage());
			return response;

		}

	}

	public Response<UtenteDto, Status> byEmail(String email) {
		Response<UtenteDto, Status> response = new Response<>();

		try {
			response.setData(UtenteMapper.toDto(uRepository.findByEmail(email)));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente trovato");
			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione(e.getMessage());
			return response;
		}
	}
	
	public Response<UtenteDto,Status> complete(Long id){
		Response<UtenteDto, Status> response = new Response<>();

		try {
			Utente u = uRepository.findById(id).get();
			u.setCompleted(true);
			uRepository.save(u);
			response.setData(UtenteMapper.toDto(u));
			response.setStatus(Status.OK);
			response.setDescrizione("Utente completato");
			return response;
		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione(e.getMessage());
			return response;
		}
	}
}
