package com.sing.ren.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
			map.put("person_id", getNewPersonId());
			accountDAO.insertDB(map);
			personDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String,Object>> query(Map<String,Object> map) {
		String personId = MapUtils.getString(map, "person_id", "");
		String name = MapUtils.getString(map, "name", "");
		String phone = MapUtils.getString(map, "phone", "");
		String email = MapUtils.getString(map, "email", "");
		if (StringUtils.isNotBlank(personId)){
			map.put("like_person_id", "%".concat(personId).concat("%"));
		}
		if (StringUtils.isNotBlank(name)){
			map.put("like_name", "%".concat(name).concat("%"));
		}
		if (StringUtils.isNotBlank(phone)){
			map.put("like_phone", "%".concat(phone).concat("%"));
		}
		if (StringUtils.isNotBlank(email)){
			map.put("like_email", "%".concat(email).concat("%"));
		}
		
		try {
			return personDAO.likeQueryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> queryPerson(Map<String, Object> map) {
		// TODO Auto-generated method stub
		try {
			return personDAO.queryOne(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			accountDAO.deleteDB(map);
			personDAO.deleteDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upsert(Map<String,Object> map) {
		try {
			accountDAO.upsertDB(map);
			personDAO.upsertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> queryAccount(Map<String, Object> map) {
		try {
			return accountDAO.queryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getNewAcc(Map<String, Object> map) throws Exception {
		Map<String,Object> result=new HashMap<>();
		result.put("account", getNewPersonId());
		return result;
	}
	
	private String getNewPersonId() throws Exception {
		Map<String,Object> param=new HashMap<>();
		param.put("rowLimit", "1");
		param.put("order", "person_id DESC");
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyyMM"); 
		String sdate = nowdate.format(new java.util.Date());
		param.put("like_person_id",sdate+"%");
		List<Map<String,Object>> personIdList=personDAO.likeQueryDB(param);
		
		String number = "";
		if (personIdList.size() > 0){
			int count = Integer.parseInt(personIdList.get(0).get("person_id").toString().substring(6,9))+1;
			
			if (count < 10){
				number = personIdList.get(0).get("person_id").toString().substring(0, 6).concat("00").concat(String.valueOf(count));
			}else if (count < 100){
				number = personIdList.get(0).get("person_id").toString().substring(0, 6).concat("0").concat(String.valueOf(count));
			}else if (count < 1000){
				number = personIdList.get(0).get("person_id").toString().substring(0, 6).concat(String.valueOf(count));
			}
		}else{
			number = sdate.concat("001");
		}
		
		return comm.verificationNumber(number);
	}
}
