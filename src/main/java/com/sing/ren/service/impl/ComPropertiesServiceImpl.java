package com.sing.ren.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.ClassLevelDAO;
import com.sing.ren.dao.table.ClassPlaceDAO;
import com.sing.ren.dao.table.ClassTypeDAO;
import com.sing.ren.service.ComPropertiesService;
import com.sing.ren.service.RSService;

@Service
public class ComPropertiesServiceImpl extends RSService implements ComPropertiesService{
	
	@Autowired
	ClassTypeDAO classTypeDAO;
	
	@Autowired
	ClassLevelDAO classLevelDAO;
	
	@Autowired
	ClassPlaceDAO classPlaceDAO;
	
	@Override
	public List<Map<String, Object>> queryClassType(Map<String, Object> map) {
		try {
			return classTypeDAO.queryDB(new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryClassLevel(Map<String, Object> map) {
		try {
			return classLevelDAO.queryDB(new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryClassPlace(Map<String, Object> map) {
		try {
			return classPlaceDAO.queryDB(new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
