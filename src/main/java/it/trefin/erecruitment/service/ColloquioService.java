package it.trefin.erecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.trefin.erecruitment.repository.ColloquioRepository;

@Service
public class ColloquioService {

	@Autowired
	private ColloquioRepository cRepository;
}
