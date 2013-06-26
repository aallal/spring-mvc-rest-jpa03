package com.serisoft.dao;

import java.util.List;

import com.serisoft.model.Personne;

public interface IPersoDao {
	
	List<Personne> findAll();
	Personne findById(Integer id);
	Boolean save(Personne perso);
//	List<Personne> findAll(int page, int pageSize);
//	List<Personne> findByPrenom(String prenom, int page, int pageSize);
	Boolean deleteOne(Integer id );
	
}
