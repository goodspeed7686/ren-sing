package com.sing.ren.service;

import java.util.List;

import com.sing.ren.pojo.ClassDetail;

public interface CurriculumService {

	public void insert(ClassDetail dto);
	public List<ClassDetail> query(ClassDetail dto);
	public void update(ClassDetail dto);
	public void delete(ClassDetail dto);
	
}
