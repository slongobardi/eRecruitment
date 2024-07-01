package it.trefin.erecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trefin.erecruitment.service.CandidaturaService;

@RestController
@RequestMapping("api/candidatura")
public class CandidaturaController {

	@Autowired
	private CandidaturaService cService;
}
