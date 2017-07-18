package com.sing.ren.service;

import java.util.List;
import java.util.Map;

import com.sing.ren.pojo.ClassDetail;


public interface CurriculumService {

	public void insert(Map<String,Object> map);
	public List<ClassDetail> query(Map<String,Object> map);
	public void update(Map<String,Object> map);
	public void delete(Map<String,Object> map);
	public void upsert(Map<String,Object> map);
}
