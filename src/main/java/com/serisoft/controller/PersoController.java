package com.serisoft.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serisoft.dao.IPersoDao;
import com.serisoft.model.Personne;

@Controller
@RequestMapping("/rest/personne")
public class PersoController {

	@Autowired
	private IPersoDao dao;

	//

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Personne> getPersonnes() {
		System.out.println(dao.findAll().toString());
		return dao.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void addPersonne(@RequestBody Personne perso) {
		dao.save(perso);

	}

	@RequestMapping(value="{id}" , method = RequestMethod.PUT)
	@ResponseBody
	public void updatePersonne(@PathVariable Integer id, @RequestBody Personne perso) {
		Personne p = dao.findById(id);
		
		p.edit(perso);
		dao.save(p);

	}

	@RequestMapping(value="{id}" , method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePersonne(@PathVariable Integer id) {
			
		dao.deleteOne(id);
		

	}
	
}
