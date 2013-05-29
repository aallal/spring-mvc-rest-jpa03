package com.serisoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serisoft.model.Personne;

@Repository
public class PersoDao implements IPersoDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional()
	public void deleteOne(Integer id) {
		Personne p = this.findById(id);
		if (p != null) {
			em.remove(p);
		}

	}

	@Transactional(readOnly = true)
	public List<Personne> findAll() {
		return em.createQuery("select p from Personne p", Personne.class)
				.getResultList();

	}

	@Transactional(readOnly = true)
	public Personne findById(Integer id) {
		return em.find(Personne.class, id);

	}

	@Transactional
	public Personne save(Personne perso) {
		if (perso.getId() == null) {
			em.persist(perso);
			return perso;
		} else {
			return em.merge(perso);
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
