package com.serisoft.dao;

public class DaoException extends RuntimeException {

	// code erreur
	private int code;

	public int getCode() {
		return code;
	}

	// constructeur
	public DaoException(String message, int code) {
		super(message);
		this.code = code;
	}
}