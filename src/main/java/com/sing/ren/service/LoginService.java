package com.sing.ren.service;

import java.util.Map;

/**
 * query for login user
 * 
 * @author TAC
 *
 */
public interface LoginService {
	
	/**
	 * Queries for data by user_id
	 * @param user_id
	 * @return user and groups data
	 */
	public Map<String,Object> processLogin(Map<String,Object> map) throws Exception;
	
}
