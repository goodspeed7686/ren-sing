package com.sing.ren.dao;

import java.util.List;

public interface BaseAccessInterface<T> {

	public List<?> query() throws Exception;
	
	public List<?> query(T params) throws Exception;
	
}
