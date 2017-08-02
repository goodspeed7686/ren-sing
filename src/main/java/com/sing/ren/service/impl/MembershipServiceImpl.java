package com.sing.ren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.common.CommonTools;
import com.sing.ren.dao.table.AccountDAO;
import com.sing.ren.dao.table.PersonDAO;
import com.sing.ren.service.MembershipService;
import com.sing.ren.service.RSService;

@Service
public class MembershipServiceImpl extends RSService implements MembershipService	{
	
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	PersonDAO personDAO;
	
	CommonTools comm=new CommonTools();
	@Override
	public void insert(Map<String,Object> map) {
		try {
//			map.put("account", "p1");
//			map.put("password", "5566");
//			map.put("role", "0");
//			map.put("set", "9");
			accountDAO.insert(map);
			personDAO.insert(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String,Object>> query(Map<String,Object> map) {
		try {
		//	map.put("type", "0");
			List<Map<String,Object>> list=null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void update(Map<String,Object> map) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Map<String,Object> map) {
		try {
	//		map.put("account","p1");
			accountDAO.delete(map);
			personDAO.delete(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upsert(Map<String,Object> map) {
		try {
//			map.put("account", "p1");
//			map.put("password", "77989");
//			map.put("role", "1");
//			map.put("set", "9");
			accountDAO.upsert(map);
			personDAO.upsert(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
