package br.eti.krebscode.ecommercespringboot.services.exceptions;

public class DataIntegrivityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrivityException (String msg) {
		super(msg);
	}
	
	public DataIntegrivityException (String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
