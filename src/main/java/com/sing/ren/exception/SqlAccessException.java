package com.sing.ren.exception;

/**
 * In case of sql exception was broken
 * @author oliver
 *
 */
public class SqlAccessException extends RuntimeException{

	private static final long serialVersionUID = -6650315680267399809L;

	public SqlAccessException(String msg){
		super(msg);
	}

}