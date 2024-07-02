package it.trefin.erecruitment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.dto.TipologiaDto;
import it.trefin.erecruitment.mapper.TipologiaMapper;
import it.trefin.erecruitment.model.Response;
import it.trefin.erecruitment.model.Response.Status;
import it.trefin.erecruitment.model.Tipologia;
import it.trefin.erecruitment.repository.TipologiaRepository;

@Service
public class TipologiaService {

	@Autowired
	private TipologiaRepository tRepository;
	
	
	
	public Response<Tipologia, Status> inserisciTipologia(Tipologia tipologia) {

		Response<Tipologia, Status> response = new Response<>();

		try {

			tRepository.save(tipologia);
			response.setData(tipologia);
			response.setStatus(Status.OK);
			response.setDescrizione("Tipologia salvata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("inserisciTipologia in errore " + e.getMessage());
			return response;

		}

	}

	public Response<TipologiaDto, Status> visualizzaTipologia(long id) {

		Response<TipologiaDto, Status> response = new Response<>();

		try {

			Tipologia tipologia = tRepository.findById(id).get();

			if (tipologia != null) {
				TipologiaDto dto = TipologiaMapper.toDto(tipologia);
				response.setData(dto);
				response.setStatus(Status.OK);
				response.setDescrizione("risultati ritornati con successo");
			} else {
				response.setStatus(Status.SYSTEM_ERROR);
				response.setDescrizione("Nessuna tipologia trovata con l'ID fornito");
			}

		} catch (Exception e) {
			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTipologia in errore: " + e.getMessage());
		}

		return response;
	}

	public Response<TipologiaDto, Status> aggiornaTipologia(Tipologia tipologia, Long id) {

		Response<TipologiaDto, Status> response = new Response<>();

		try {

			Tipologia t =  tRepository.findById(id).get();
	
			t.setNome(tipologia.getNome());
			
			
			tRepository.save(t);
			response.setData(TipologiaMapper.toDto(t));
			response.setStatus(Status.OK);
			response.setDescrizione("tipologia modificata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("aggiornaTipologia in errore " + e.getMessage());
			return response;

		}

	}

	public Response<Tipologia, Status> eliminaTipologia(long id) {

		Response<Tipologia, Status> response = new Response<>();

		try {

			response.setData((tRepository.findById(id).get()));
			tRepository.delete(response.getData());
			response.setStatus(Status.OK);
			response.setDescrizione("tipologia eliminata con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("eliminaTipologia in errore " + e.getMessage());
			return response;

		}

	}

	public Response<List<TipologiaDto>, Status> visualizzaTutteTipologie() {

		Response<List<TipologiaDto>, Status> response = new Response<>();

		try {

			response.setData(tRepository.findAll().stream().map(TipologiaMapper::toDto)
					.collect(Collectors.toList()));
			response.setStatus(Status.OK);
			response.setDescrizione("Tipologie ritornate con successo.");
			return response;

		} catch (Exception e) {

			response.setStatus(Status.SYSTEM_ERROR);
			response.setDescrizione("visualizzaTutteTipologie in errore " + e.getMessage());
			return response;

		}

	}
}
