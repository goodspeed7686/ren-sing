package com.sing.ren.service;

import java.util.List;
import java.util.Map;

public interface PersonService {

	public List<Map<String,Object>> getPerson();
	public List<Map<String,Object>> query(Map<String,Object> map);
	public List<Map<String,Object>> queryForRole(Map<String,Object> map);
}
