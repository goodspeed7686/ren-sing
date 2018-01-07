package com.sing.ren.service;

import java.util.List;
import java.util.Map;


public interface ClassService {

	public List<Map<String,Object>> getMaster();
	public List<Map<String,Object>> query(Map<String,Object> map);
	public List<Map<String,Object>> queryDetail(Map<String,Object> map) throws Exception;
	public void insert(Map<String,Object> map);
	public void update(Map<String,Object> map);
	public Map<String, Object> queryMasterCount(Map<String,Object> map) throws Exception;
}
