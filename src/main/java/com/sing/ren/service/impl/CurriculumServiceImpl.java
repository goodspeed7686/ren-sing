package com.sing.ren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.ClassDetailDAO;
import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.service.CurriculumService;
import com.sing.ren.service.RSService;

@Service
public class CurriculumServiceImpl extends RSService implements CurriculumService	{
	
	@Autowired
	ClassDetailDAO classDetailDAO;

	@Override
	public List<ClassDetail> getDetail(Map<String, Object> map) {
		try {
			return classDetailDAO.getDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<ClassDetail> getDetail(ClassDetail map) {
		try {
			return classDetailDAO.getDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void deleteDetail(Map<String, Object> map) {
		try {
			 classDetailDAO.deleteDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateDetail(Map<String, Object> map) {
		try {
			classDetailDAO.updateDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertDetail(Map<String, Object> map) {
		try {
		  classDetailDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertDetail(ClassDetail map) {
		try {
		  classDetailDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
