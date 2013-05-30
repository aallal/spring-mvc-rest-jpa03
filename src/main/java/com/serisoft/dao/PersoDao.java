package com.serisoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serisoft.model.Personne;
import static com.serisoft.model.Define.*;

@Repository
public class PersoDao implements IPersoDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional()
	public void deleteOne(Integer id) {

		Personne p = this.findById(id);
		if (p != null) {
			em.remove(p);
		} else {
			throw new DaoException(String.format(msg_person_id_not_found, id),cs_id_not_found);
		}

	}

	@Transactional(readOnly = true)
	public List<Personne> findAll() {
		return em.createQuery("select p from Personne p", Personne.class)
				.getResultList();

	}

	@Transactional(readOnly = true)
	public Personne findById(Integer id) {
		Personne perso = em.find(Personne.class, id);
		if (perso == null) {
			throw new DaoException(String.format(msg_person_id_not_found, id),
					cs_id_not_found);

		}
		return perso;
	}

	@Transactional
	public Personne save(Personne perso) {
		try {
			if (perso.getId() == null) {
				em.persist(perso);
			} else {
				perso = em.merge(perso);
			}

			return perso;
		} catch (Exception e) {
			throw new DaoException(msg_error_save_person, cs_error_save);

		}
	}

	@Transactional(readOnly = true)
	public List<Personne> findAll(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<Personne> findByPrenom(String prenom, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
