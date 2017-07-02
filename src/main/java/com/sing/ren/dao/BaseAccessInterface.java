package com.sing.ren.dao;

import java.util.List;
import java.util.Map;

public interface BaseAccessInterface {

	public List<Map<String, Object>> query() throws Exception;
	
	public List<Map<String, Object>> query(Map<String, Object> params) throws Exception;
	
}
