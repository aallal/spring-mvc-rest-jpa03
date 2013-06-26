package com.serisoft.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.serisoft.dto.PersoDto;
import com.serisoft.model.Personne;

public class PersoMapper {

	public static PersoDto map(Personne perso) {
			PersoDto dto = new PersoDto();
			dto.setId(perso.getId());
			dto.setNom(perso.getNom());
			dto.setPrenom(perso.getPrenom());
			dto.setMarie(perso.getMarie());
			dto.setNbenfants(perso.getNbenfants());
			dto.setAge(perso.getAge());
			
			return dto;
	}
	
	public static List<PersoDto> map(Page<Personne> persos) {
		List<PersoDto> dtos = new ArrayList<PersoDto>();
		for (Personne perso: persos) {
			dtos.add(map(perso));
		}
		return dtos;
	}
}
