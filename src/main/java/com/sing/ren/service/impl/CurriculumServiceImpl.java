package com.sing.ren.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.ClassDetailDAO;
import com.sing.ren.dao.table.ClassMasterDAO;
import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.service.CurriculumService;
import com.sing.ren.service.RSService;

@Service
public class CurriculumServiceImpl extends RSService implements CurriculumService	{
	
	@Autowired
	ClassDetailDAO classDetailDAO;
	@Autowired
	ClassMasterDAO classMasterDAO;

	@Override
	public void insert(Map<String,Object> map) {
		try {
		  Map<String,Object> masterId=new HashMap<String,Object>();
		  masterId.put("classMasterId",map.get("classMasterId"));
		  List<Map<String, Object>> classMaster=classMasterDAO.queryDB(masterId);
		  
		  classMasterDAO.updateDB(map);
		  classDetailDAO.insertDB(map);
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String,Object>> query(Map<String,Object> map) {
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
