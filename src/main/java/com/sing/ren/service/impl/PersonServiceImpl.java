package com.sing.ren.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.dao.table.AccountDAO;
import com.sing.ren.dao.table.PersonDAO;
import com.sing.ren.service.PersonService;
import com.sing.ren.service.RSService;

@Service
public class PersonServiceImpl extends RSService implements PersonService{
	
	@Autowired
	PersonDAO personDAO;
	
	@Autowired
	AccountDAO accountDAO;

	@Override
	public List<Map<String,Object>> getPerson() {
		try {
			return personDAO.queryDB(new HashMap<String,Object>());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Map<String, Object> map) {
		try {
			return personDAO.queryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForRole(Map<String, Object> map) {
		try {
			return accountDAO.queryForJoinPerson(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
