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
	public void insert(Map<String,Object> map) {
		try {
		  classDetailDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ClassDetail> query(Map<String,Object> map) {
		try {
			return classDetailDAO.queryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void update(Map<String,Object> map) {
		try {
			classDetailDAO.updateDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Map<String,Object> map) {
		try {
			 classDetailDAO.deleteDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upsert(Map<String,Object> map) {
		try {
			 classDetailDAO.upsertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
