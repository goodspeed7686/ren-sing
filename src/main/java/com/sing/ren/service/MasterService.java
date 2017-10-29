package com.sing.ren.service;

import java.util.List;
import java.util.Map;


public interface MasterService {

	public List<Map<String,Object>> getMaster();
	public List<Map<String,Object>> query(Map<String,Object> map);
}
