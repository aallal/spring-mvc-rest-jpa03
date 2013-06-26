package com.serisoft.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.serisoft.dao.IPersoDao;
import com.serisoft.repository.PersoRepository;
import com.serisoft.dto.PersoDto;
import com.serisoft.dto.StatusResponse;
import com.serisoft.model.JqgridResponse;
import com.serisoft.model.Personne;
import com.serisoft.util.PersoMapper;
import com.serisoft.utils.JqgridFilter;
import com.serisoft.utils.JqgridObjectMapper;

@Controller
@RequestMapping("/rest/personne3")
public class PersoController3 {

	@Autowired
	private PersoRepository repository;

	@Autowired
	private IPersoDao dao;

	private static final Logger logger_c = Logger
			.getLogger(PersoController3.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getPersosPage() {
		logger_c.debug("call getPersosPage  method  of PersoController3");
		return "personnes";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/records", produces = "application/json")
	@ResponseBody
	public JqgridResponse<PersoDto> records(
			@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		logger_c.debug("Returing personnes from method records() of PersoController3");

		Pageable pageRequest = new PageRequest(page - 1, rows);

		if (search == true) {
			return getFilteredRecords(filters, pageRequest);

		}

		Page<Personne> persos = repository.findAll(pageRequest);
		logger_c.debug("Returing personnes [persos var]: " + persos.toString());

		List<PersoDto> persoDtos = PersoMapper.map(persos);

		JqgridResponse<PersoDto> response = new JqgridResponse<PersoDto>();
		response.setRows(persoDtos);
		response.setRecords(Long.valueOf(persos.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(persos.getTotalPages()).toString());
		response.setPage(Integer.valueOf(persos.getNumber() + 1).toString());

		return response;
	}

	/**
	 * Helper method for returning filtered records
	 */
	public JqgridResponse<PersoDto> getFilteredRecords(String filters,
			Pageable pageRequest) {
		String qNom = null;
		String qPrenom = null;
		Short qAge = null;

		JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
		for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
			if (rule.getField().equals("nom"))
				qNom = rule.getData();
			else if (rule.getField().equals("prenom"))
				qPrenom = rule.getData();
			else if (rule.getField().equals("age"))
				qAge = Short.valueOf(rule.getData());
		}

		Page<Personne> persos = null;
		if (qNom != null)
			persos = repository.findByNomLike("%" + qNom + "%", pageRequest);
		if (qNom != null && qPrenom != null)
			persos = repository.findByNomLikeAndPrenomLike("%" + qNom + "%",
					"%" + qPrenom + "%", pageRequest);
		if (qPrenom != null)
			persos = repository.findByPrenomLike("%" + qPrenom + "%",
					pageRequest);

		if (qAge != null)
			persos = repository.findByAge(qAge, pageRequest);

		List<PersoDto> persosDtos = PersoMapper.map(persos);
		JqgridResponse<PersoDto> response = new JqgridResponse<PersoDto>();
		response.setRows(persosDtos);
		response.setRecords(Long.valueOf(persos.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(persos.getTotalPages()).toString());
		response.setPage(Integer.valueOf(persos.getNumber() + 1).toString());
		return response;
	}

	@RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse create(@RequestParam String nom,
			@RequestParam String prenom, @RequestParam Short age) {

		Personne newPerso = new Personne(nom, prenom, age);

		Boolean result = dao.save(newPerso);
		return new StatusResponse(result);
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse update(@RequestParam Integer id,
			@RequestParam String nom, @RequestParam String prenom,
			@RequestParam Short age) {
		Personne perso = dao.findById(id);
		Boolean result = false;
		String message = "";
		if (perso != null) {
			perso.setNom(nom);
			perso.setPrenom(prenom);
			perso.setAge(age);
			result = dao.save(perso);
		} else {
			result = false;
			message = "Impossible de modifier la personne";
		}

		return new StatusResponse(result, message);
	}
	
	@RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public StatusResponse delete(@RequestParam Integer id) {
		logger_c.debug("call delete method with id= "+id);
		Boolean result  = dao.deleteOne(id) ;
		String message = "";
		if ( !result) {
			message="Impossible de suprimer la personne de id ["+id+"]" ;
		} 

		return new StatusResponse(result, message);
	}
	
}
