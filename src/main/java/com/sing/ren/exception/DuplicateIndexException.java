package com.sing.ren.exception;


/**
 * While index is duplicate
 * @author oliver
 *
 */
public class DuplicateIndexException extends AccessException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateIndexException(String msg){
		super(msg);
	}

}
