package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.AziendaRepository;

@Service
public class AziendaService {

	@Autowired
	private AziendaRepository aRepository;
}
