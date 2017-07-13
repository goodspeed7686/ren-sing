package com.sing.ren.service;

import java.util.List;
import java.util.Map;

import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.pojo.ClassMaster;

public interface CurriculumService {

	public List<ClassDetail> query(Map<String, Object> map);
	public List<ClassMaster> query(ClassDetail dto);
	public void delete(ClassDetail dto);
	public void update(ClassDetail dto);
	public void insert(Map<String, Object> map);
	public void insert(ClassDetail dto);
}
