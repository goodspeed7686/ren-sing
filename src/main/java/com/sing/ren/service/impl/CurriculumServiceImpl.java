package com.sing.ren.service.impl;

import java.util.Date;
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
		  Map<String,Object> time=new HashMap<String,Object>();
		  time.put("start_time",map.get("time"));
		  List<Map<String, Object>> coursesTime=coursesTimeDAO.queryDB(time);
		  map.put("time",coursesTime.get(0).get("id"));
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
//			for(Map<String,Object> map2 :list){
//				if(MapUtils.getString(map2, "type","").equals("0")){
//					map2.put("time",comm.timeManage(MapUtils.getString(map2, "time",""),"+",10));
//				}else{
//					map2.put("time", comm.timeManage(MapUtils.getString(map2, "time",""),"",0));
//				}
//			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Map<String,Object>> queryBreak(Map<String,Object> map) {
		try {
			List<Map<String,Object>> coursesTimeList=coursesTimeDAO.queryDB(new HashMap<String,Object>());
			List<Map<String,Object>> classDetail=classDetailDAO.queryDB(map);
			
			for (Map<String,Object> detailMap : classDetail) {
				String date = MapUtils.getString(detailMap, "date", "");
				String time = MapUtils.getString(detailMap, "time", "");
				String range = MapUtils.getString(detailMap, "ranges", "");
				String sTime = MapUtils.getString(detailMap, "s_time", "");
				String eTime = MapUtils.getString(detailMap, "e_time", "");
				if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(range)) {
					for (int i=Integer.parseInt(time) ; i<=Integer.parseInt(time)+Integer.parseInt(range) ; i++){
						for (int j=0;j<coursesTimeList.size();j++){
							if (coursesTimeList.get(j).get("id").equals(i)){
								coursesTimeList.remove(j);
							}
						}
					}
				} else if (StringUtils.isNotBlank(sTime) && StringUtils.isNotBlank(eTime)) {
					String courseStartIndex = "";
					Boolean startAtThis = true;
					for (int j=0; j<coursesTimeList.size(); j++) {
						Map<String,Object> courseMap = coursesTimeList.get(j);
						String courseStartTime = MapUtils.getString(courseMap, "start_time", "");
						Date courseTimeForDate = CommonTools.getStrDateChangeDate(date, courseStartTime, CommonTools.dateFormat[3]);
						Date sTimeForDate = CommonTools.getStrDateChangeDate(date, sTime, CommonTools.dateFormat[3]);
						Date eTimeForDate = CommonTools.getStrDateChangeDate(date, sTime, CommonTools.dateFormat[3]);
						if (courseTimeForDate.after(sTimeForDate) && courseTimeForDate.before(eTimeForDate)) {
							if (startAtThis) {
								courseStartIndex = MapUtils.getString(courseMap, "id", "");
								startAtThis = false;
							}else {
								coursesTimeList.remove(j);
								break;
							}
						}
						if (StringUtils.isNotBlank(courseStartIndex)) {
							coursesTimeList.remove(j);
						}
					}
				}
			}
			
			return  coursesTimeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> queryRestClass(Map<String,Object> map) {
		try {
			//課程狀態
			map.put("status", "0");
			//個人課代碼
//			map.put("type", "0");
			List<Map<String,Object>> result=classMasterDAO.queryDB(map);

			return  result;
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
