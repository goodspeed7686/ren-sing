package com.sing.ren.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.ClassMasterDAO;
import com.sing.ren.pojo.ClassMaster;
import com.sing.ren.service.MasterService;
import com.sing.ren.service.RSService;

@Service
public class MasterServiceImpl extends RSService implements MasterService{
	
	@Autowired
	ClassMasterDAO classMasterDAO;

	@Override
	public List<Map<String,Object>> getMaster() {
		try {
			return classMasterDAO.queryDB(new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Map<String, Object> map) {
		try {
			return classMasterDAO.queryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
