package com.serisoft.dto;

/**
 * 
 */

public class PersoDto implements java.io.Serializable {

	private Integer id;
	private Integer version;
	private String nom;
	private String prenom;
	private Short age;
	private Short nbenfants;
	private short marie;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Short getNbenfants() {
		return nbenfants;
	}

	public void setNbenfants(Short nbenfants) {
		this.nbenfants = nbenfants;
	}

	public short getMarie() {
		return marie;
	}

	public void setMarie(short marie) {
		this.marie = marie;
	}


	public PersoDto() {
	}


	
}
