package com.sing.ren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.ClassDetailDAO;
import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.pojo.ClassMaster;
import com.sing.ren.service.CurriculumService;
import com.sing.ren.service.RSService;

@Service
public class CurriculumServiceImpl extends RSService implements CurriculumService	{
	
	@Autowired
	ClassDetailDAO classDetailDAO;

	@Override
	public List<ClassDetail> query(Map<String, Object> map) {
		try {
			return classDetailDAO.getDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<ClassMaster> query(ClassDetail dto) {
		try {
			return classDetailDAO.getDetail(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void delete(ClassDetail dto) {
		try {
			 classDetailDAO.deleteDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(ClassDetail dto) {
		try {
			classDetailDAO.updateDetail(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insert(Map<String, Object> map) {
		try {
		  classDetailDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insert(ClassDetail dto) {
		try {
		  classDetailDAO.insertDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
