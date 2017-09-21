package com.sing.ren.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional (rollbackFor = Exception.class)
	public void insert(Map<String,Object> map) {
		try {
//			map.put("account", "p1");
//			map.put("password", "5566");
//			map.put("role", "0");
//			map.put("set", "9");
			Map<String,Object> param=new HashMap<>();
			param.put("rowLimit", "1");
			param.put("order", "person_id DESC");
			SimpleDateFormat nowdate = new SimpleDateFormat("yyMMdd"); 
			String sdate = nowdate.format(new java.util.Date());
			param.put("max_student_id",sdate+"%");
			List<Map<String,Object>> personId=personDAO.queryDB(param);
			int number=Integer.parseInt(personId.get(0).get("person_id").toString().substring(0, 9))+1;
			map.put("person_id", comm.verificationNumber(number+""));
			accountDAO.insertDB(map);
			personDAO.insertDB(map);
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
			accountDAO.deleteDB(map);
			personDAO.deleteDB(map);
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
			accountDAO.upsertDB(map);
			personDAO.upsertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
