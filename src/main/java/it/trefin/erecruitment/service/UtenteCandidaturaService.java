package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.UtenteCandidaturaRepository;

@Service
public class UtenteCandidaturaService {

	@Autowired
	private UtenteCandidaturaRepository ucRepository;
}
