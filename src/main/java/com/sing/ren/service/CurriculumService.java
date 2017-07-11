package com.sing.ren.service;

import java.util.List;
import java.util.Map;

import com.sing.ren.pojo.ClassDetail;

public interface CurriculumService {

	public List<ClassDetail> getDetail(Map<String, Object> map);
	public List<ClassDetail> getDetail(ClassDetail map);
	public void deleteDetail(Map<String, Object> map);
	public void updateDetail(Map<String, Object> map);
	public void insertDetail(Map<String, Object> map);
	public void insertDetail(ClassDetail map);
}
