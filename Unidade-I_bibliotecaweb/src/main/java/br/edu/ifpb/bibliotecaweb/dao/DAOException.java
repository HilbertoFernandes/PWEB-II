package br.edu.ifpb.bibliotecaweb.dao;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -5839726585588766122L;

	public DAOException() {
		super();
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(String arg0) {
		super(arg0);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}
}
