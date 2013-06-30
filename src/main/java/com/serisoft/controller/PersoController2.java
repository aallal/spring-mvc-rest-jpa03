package com.serisoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.serisoft.dao.IPersoDao;
import com.serisoft.model.Personne;

@Controller
@RequestMapping("/rest/personne2")
public class PersoController2 {

	@Autowired
	private IPersoDao dao;

	@Autowired
	private View jsonView_i;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	private static final Logger logger_c = Logger
			.getLogger(PersoController2.class);

	//
	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 *            the s message
	 * @return the model and view
	 */
	private ModelAndView createErrorResponse(String sMessage) {
		logger_c.debug("Exception déclenché au niveau  de la classe PersoController2 :["
				+ sMessage + " ]");
		return new ModelAndView(jsonView_i, ERROR_FIELD, sMessage);
	}

	@RequestMapping(value = "/perso", method = RequestMethod.GET)
	public String getAjaxAddPage() {
		logger_c.debug("Received request to show AJAX, add page");

		// This will resolve to /WEB-INF/view/ajax-perso.jsp
		return "ajaxpersopage";

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPersonnes() {
		List<Personne> personnes = null;

		try {
			personnes = dao.findAll();

		} catch (Exception e) {
			String sMessage = "Error getting all personnes. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returing personnes : " + personnes.toString());
		System.out.println(personnes.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, personnes);
	}

	public static boolean isEmpty(String s_p) {
		return (null == s_p) || s_p.trim().length() == 0;
	}

	@RequestMapping(value = "{persoId}", method = RequestMethod.GET)
	public ModelAndView getPersonneById(@PathVariable("persoId") Integer id) {
		Personne personne = null;

		/* validate fund Id parameter */
		if ((id == null) || (id < 0)) {
			String sMessage = "Error invoking getFund - Invalid fund Id parameter";
			return createErrorResponse(sMessage);
		}

		try {
			personne = dao.findById(id);
		} catch (Exception e) {
			String sMessage = "Error getting personne. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returing personnes : " + personne.toString());
		System.out.println(personne.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, personne);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public ModelAndView createPersonne(@RequestBody Personne perso_p,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		logger_c.debug("Creating Personne: " + perso_p.toString());

		try {

			dao.save(perso_p);

		} catch (Exception e) {
			String sMessage = "Error creating new Personne. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response code */
		httpResponse_p.setStatus(HttpStatus.CREATED.value());

		/* set location of created resource */
		httpResponse_p.setHeader("Location", request_p.getContextPath()
				+ "/rest/personne2/" + perso_p.getId());

		/**
		 * Return the view
		 */
		return new ModelAndView(jsonView_i, DATA_FIELD, perso_p);
	}

	@RequestMapping(value = "{id}", method = { RequestMethod.PUT })
	public ModelAndView editPersonne(@PathVariable Integer id,
			@RequestBody Personne perso_p, HttpServletResponse httpResponse_p,
			WebRequest request_p) {

		logger_c.debug("Editing Personne: " + perso_p.toString());

		/* validate fund Id parameter */
		if ((id == null) || (id < 0)) {
			String sMessage = "Error invoking getFund - Invalid fund Id parameter";
			return createErrorResponse(sMessage);
		}
		Personne perso = null;
		try {
			perso = dao.findById(id);
			perso.edit(perso_p);

			dao.save(perso);

		} catch (Exception e) {
			String sMessage = "Error editing new Personne. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response code */
		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, perso);

	}

	@RequestMapping(value = "{id}", method = { RequestMethod.DELETE })
	public ModelAndView delPersonne(@PathVariable Integer id,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		logger_c.debug("Deleting Personne: " + id);

		/* validate fund Id parameter */
		if ((id == null) || (id < 0)) {
			String sMessage = "Error invoking getFund - Invalid fund Id parameter";
			return createErrorResponse(sMessage);
		}

		try {

			dao.deleteOne(id);

		} catch (Exception e) {
			String sMessage = "Error deleting new Personne. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response code */
		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, null);

	}

}
