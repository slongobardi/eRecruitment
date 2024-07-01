package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository uRepository;
}
