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
import com.sing.ren.dao.table.CoursesTimeDAO;
import com.sing.ren.service.CurriculumService;
import com.sing.ren.service.RSService;

@Service
public class CurriculumServiceImpl extends RSService implements CurriculumService	{
	
	@Autowired
	ClassDetailDAO classDetailDAO;
	@Autowired
	ClassMasterDAO classMasterDAO;
	@Autowired
	CoursesTimeDAO coursesTimeDAO;
	
	CommonTools comm=new CommonTools();
	@Override
	public void insert(Map<String,Object> map) {
		try {
		  Map<String,Object> masterId=new HashMap<String,Object>();
		  masterId.put("class_master_id",MapUtils.getString(map, "class_master_id",""));
		  List<Map<String, Object>> classMaster=classMasterDAO.queryDB(masterId);
		  
		  classMaster.get(0).put("count",Integer.parseInt(MapUtils.getString(classMaster.get(0), "count")+"")+1);
		  classMaster.get(0).put("rest",Integer.parseInt(MapUtils.getString(classMaster.get(0), "rest")+"")-1);
		  classMasterDAO.updateDB(classMaster.get(0));
		  classDetailDAO.insertDB(map);
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String,Object>> query(Map<String,Object> map) {
		try {
			if (StringUtils.isNotBlank(MapUtils.getString(map, "date_pre", "")) &&
				StringUtils.isNotBlank(MapUtils.getString(map, "date_now", "")) &&
				StringUtils.isNotBlank(MapUtils.getString(map, "date_next", ""))){
				map.put("date_pre", map.get("date_pre").toString().concat("%"));
				map.put("date_now", map.get("date_now").toString().concat("%"));
				map.put("date_next", map.get("date_next").toString().concat("%"));
			}
			
			List<Map<String,Object>> list=classDetailDAO.queryDB(map);
			for(Map<String,Object> map2 :list){
				if(MapUtils.getString(map2, "type","").equals("0")){
					map2.put("time",comm.timeManage(MapUtils.getString(map2, "time",""),"+",10));
				}else{
					map2.put("time", comm.timeManage(MapUtils.getString(map2, "time",""),"",0));
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Map<String,Object>> queryBreak(Map<String,Object> map) {
		try {
		//	map.put("type", "0");
			List<Map<String,Object>> coursesTime=coursesTimeDAO.queryDB(new HashMap<String,Object>());
			List<Map<String,Object>> classDetail=classDetailDAO.queryDB(map);
			for(int i = 0,len = coursesTime.size(); i <len; i++){
				coursesTime.get(i).put("start_time",comm.timeManage(MapUtils.getString(coursesTime.get(i),"start_time"),"+",10));
				coursesTime.get(i).put("end_time",comm.timeManage(MapUtils.getString(coursesTime.get(i),"end_time"),"+",10));
				for(int j = 0,len2 = classDetail.size(); j <len2; j++){
					if(MapUtils.getString(coursesTime.get(i), "start_time").equals(comm.timeManage(MapUtils.getString(classDetail.get(j), "time"),"+",10))){
						coursesTime.remove(i);
						classDetail.remove(j);
						len2--;
						j--;
						len--;
						i--;
						break;
					}
				}
			}
			return  coursesTime;
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
			if(MapUtils.getString(map,"type").equals("0")){
				map.put("time",comm.timeManage(MapUtils.getString(map,"time"),"-",10));
			}else{
				map.put("time",comm.timeManage(MapUtils.getString(map,"time"),"",0));
			}
			 classDetailDAO.upsertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
