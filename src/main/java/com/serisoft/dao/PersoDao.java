package com.serisoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serisoft.model.Personne;
import com.serisoft.repository.PersoRepository;

import static com.serisoft.model.Define.*;

@Repository
public class PersoDao implements IPersoDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired 
	private PersoRepository repo ;
	
	@Transactional()
	public Boolean deleteOne(Integer id) {

		Personne p = this.findById(id);
		if (p != null) {
			repo.delete(p);
			return true;
			//em.remove(p);
		} else {
			return false;
			//throw new DaoException(String.format(msg_person_id_not_found, id),cs_id_not_found);
		}

	}

	@Transactional(readOnly = true)
	public List<Personne> findAll() {
		return repo.findAll();
		/*return em.createQuery("select p from Personne p", Personne.class)
				.getResultList();*/

	}

	@Transactional(readOnly = true)
	public Personne findById(Integer id) {
		Personne perso = repo.findOne(id);
		
		/*Personne perso = em.find(Personne.class, id);*/
		if (perso == null) {
			throw new DaoException(String.format(msg_person_id_not_found, id),
					cs_id_not_found);

		}
		return perso;
	}

	@Transactional
	public Boolean save(Personne perso) {
		
		Personne persoSaved= repo.save(perso);
		if (persoSaved==null) {return false;}
		else return true ;
		
		/*try {
			if (perso.getId() == null) {
				em.persist(perso);
			} else {
				perso = em.merge(perso);
			}

			return perso;
		} catch (Exception e) {
			throw new DaoException(msg_error_save_person, cs_error_save);

		}*/
	}

	

}
