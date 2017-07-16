package com.sing.ren.service.impl;

import java.util.List;

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
	public void insert(ClassDetail dto) {
		try {
		  classDetailDAO.insertDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ClassDetail> query(ClassDetail dto) {
		try {
			return classDetailDAO.queryDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void update(ClassDetail dto) {
		try {
			classDetailDAO.updateDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void upsert(ClassDetail dto) {
		try {
			 classDetailDAO.upsertDB(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
