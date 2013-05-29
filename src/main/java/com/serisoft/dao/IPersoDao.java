package com.serisoft.dao;

import java.util.List;

import com.serisoft.model.Personne;

public interface IPersoDao {
	
	List<Personne> findAll();
	Personne findById(Integer id);
	Personne save(Personne perso);
	List<Personne> findAll(int page, int pageSize);
	List<Personne> findByPrenom(String prenom, int page, int pageSize);
	void deleteOne(Integer id );
	
}
