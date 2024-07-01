package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.UtenteTitoliStudioRepository;

@Service
public class UtenteTitoliStudioService {

	@Autowired
	private UtenteTitoliStudioRepository utsRepository;
}
