package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.TipologiaRepository;

@Service
public class TipologiaService {

	@Autowired
	private TipologiaRepository tRepository;
}
