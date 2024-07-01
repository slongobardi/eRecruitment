package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.TitoliStudioRepository;

@Service
public class TitoliStudioService {

	@Autowired
	private TitoliStudioRepository tsRepository;
}
