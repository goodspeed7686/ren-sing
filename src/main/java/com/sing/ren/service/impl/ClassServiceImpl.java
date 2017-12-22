package com.sing.ren.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.common.CommonTools;
import com.sing.ren.dao.table.ClassDetailDAO;
import com.sing.ren.dao.table.ClassMasterDAO;
import com.sing.ren.service.ClassService;
import com.sing.ren.service.RSService;

@Service
public class ClassServiceImpl extends RSService implements ClassService{
	
	@Autowired
	ClassMasterDAO classMasterDAO;
	
	@Autowired
	ClassDetailDAO classDetailDAO;

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
			String teacher_name = MapUtils.getString(map, "teacher_name", "");
			String student_name = MapUtils.getString(map, "student_name", "");
			if (StringUtils.isNotBlank(teacher_name)){
				map.put("like_teacher_name", "%".concat(teacher_name).concat("%"));
			}
			if (StringUtils.isNotBlank(student_name)){
				map.put("like_student_name", "%".concat(student_name).concat("%"));
			}
			
			map.put("fromIndex", (MapUtils.getInteger(map, "page", 1) - 1)*10);
			map.put("rowLimit", 10);
			
			return classMasterDAO.queryDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Map<String, Object> map) {
		try {
			map.put("rest", MapUtils.getInteger(map, "summary") - MapUtils.getInteger(map, "count"));
			map.put("e_date", CommonTools.getAddMonthAfterDate(MapUtils.getString(map, "s_date"), 8, "yyyy/MM/dd"));
			classMasterDAO.insertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Map<String, Object> map) {
		try {
			map.put("rest", MapUtils.getInteger(map, "summary") - MapUtils.getInteger(map, "count"));
			map.put("e_date", CommonTools.getAddMonthAfterDate(MapUtils.getString(map, "s_date"), 8, "yyyy/MM/dd"));
			classMasterDAO.update(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String,Object>> getDetailList(Map<String, Object> map) throws Exception {
		map.put("fromIndex", (MapUtils.getInteger(map, "page", 1) - 1)*10);
		map.put("rowLimit", 10);
		classDetailDAO.query(map);
		
		return null;
	}

	@Override
	public Map<String, Object> queryMasterCount(Map<String, Object> map) throws Exception {
		String teacher_name = MapUtils.getString(map, "teacher_name", "");
		String student_name = MapUtils.getString(map, "student_name", "");
		if (StringUtils.isNotBlank(teacher_name)){
			map.put("like_teacher_name", "%".concat(teacher_name).concat("%"));
		}
		if (StringUtils.isNotBlank(student_name)){
			map.put("like_student_name", "%".concat(student_name).concat("%"));
		}
		
		return classMasterDAO.queryCountDB(map);
	}
	
}
