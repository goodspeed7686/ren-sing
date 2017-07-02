package com.sing.ren.exception;

/**
 * While index is duplicate
 * @author oliver
 *
 */
public class AccessException extends RuntimeException {
	
	private static final long serialVersionUID = 3424549974793063227L;

	public AccessException(String msg){
		super(msg);
	}

}
