package com.sing.ren.service;

import java.util.List;
import java.util.Map;


public interface MembershipService {

	public void insert(Map<String,Object> map) throws Exception;
	public List<Map<String,Object>> query(Map<String,Object> map);
	public Map<String,Object> queryPerson(Map<String,Object> map);
	public void update(Map<String,Object> map);
	public void delete(Map<String,Object> map);
	public void upsert(Map<String,Object> map);
	public List<Map<String,Object>> queryAccount(Map<String,Object> map);
	public Map<String,Object> getNewAcc(Map<String,Object> map) throws Exception;
	public Map<String, Object> queryCount(Map<String, Object> map) throws Exception;
}
