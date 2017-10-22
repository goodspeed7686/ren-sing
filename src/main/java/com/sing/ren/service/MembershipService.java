package com.sing.ren.service;

import java.util.List;
import java.util.Map;


public interface MembershipService {

	public void insert(Map<String,Object> map);
	public List<Map<String,Object>> query(Map<String,Object> map);
	public void update(Map<String,Object> map);
	public void delete(Map<String,Object> map);
	public void upsert(Map<String,Object> map);
	public List<Map<String,Object>> validateAccount(Map<String,Object> map);
}
