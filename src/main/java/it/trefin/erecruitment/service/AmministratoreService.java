package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.AmministratoreRepository;

@Service
public class AmministratoreService {

	@Autowired
	private AmministratoreRepository  aRepository;
}
