package com.sing.ren.service;

import java.util.List;
import java.util.Map;


public interface ComPropertiesService {
	public List<Map<String,Object>> queryClassType(Map<String,Object> map);
	public List<Map<String,Object>> queryClassLevel(Map<String,Object> map);
	public List<Map<String,Object>> queryClassPlace(Map<String,Object> map);
	
}
