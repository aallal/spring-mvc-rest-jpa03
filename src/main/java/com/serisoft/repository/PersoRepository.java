package com.serisoft.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.serisoft.model.Personne;


public interface PersoRepository extends JpaRepository<Personne, Integer> {

		Personne findByNom(String nom);
		
		Page<Personne> findByNomLike(String nom, Pageable pageable);
		Page<Personne> findByPrenomLike(String prenom, Pageable pageable);
		Page<Personne> findByNomLikeAndPrenomLike(String nom, String prenom, Pageable pageable);
		Page<Personne> findByAge(Short age, Pageable pageable);
	}

