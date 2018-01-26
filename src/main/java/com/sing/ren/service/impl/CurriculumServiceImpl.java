package com.sing.ren.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sing.ren.Context;
import com.sing.ren.common.CommonTools;
import com.sing.ren.dao.table.ClassDetailDAO;
import com.sing.ren.dao.table.ClassMasterDAO;
import com.sing.ren.dao.table.ClassMasterHistoryDAO;
import com.sing.ren.dao.table.CoursesTimeDAO;
import com.sing.ren.service.CurriculumService;
import com.sing.ren.service.RSService;

@Transactional(rollbackFor = Exception.class)
@Service
public class CurriculumServiceImpl extends RSService implements CurriculumService	{
	
	private static String SUN = "Sun";
	private static String MON = "Mon";
	private static String TUE = "Tue";
	private static String WED = "Wed";
	private static String THUS = "Thus";
	private static String FRI = "Fri";
	private static String SAT = "Sat";
	
	@Autowired
	ClassDetailDAO classDetailDAO;
	@Autowired
	ClassMasterDAO classMasterDAO;
	@Autowired
	CoursesTimeDAO coursesTimeDAO;
	@Autowired
	ClassMasterHistoryDAO classMasterHistoryDAO;
	
	CommonTools comm=new CommonTools();
	@Override
	public void insert(Map<String,Object> map) {
		try {
		  Map<String,Object> masterId=new HashMap<String,Object>();
		  masterId.put("class_master_id",MapUtils.getString(map, "class_master_id",""));
		  List<Map<String, Object>> classMaster=classMasterDAO.queryDB(masterId);
		  
		  classMaster.get(0).put("count",MapUtils.getInteger(classMaster.get(0), "count") + 1);
		  classMaster.get(0).put("rest",MapUtils.getInteger(classMaster.get(0), "summary") - MapUtils.getInteger(classMaster.get(0), "count"));
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
			String date_now = MapUtils.getString(map, "date_now", "");
			if (StringUtils.isNotBlank(date_now)){
				date_now.contains("/1");
				map.put("date_pre", CommonTools.getAddMonthAfterDate(date_now, -1, "yyyy/MM").concat("%"));
				map.put("date_now", map.get("date_now").toString().concat("%"));
				map.put("date_next", CommonTools.getAddMonthAfterDate(date_now, 1, "yyyy/MM").concat("%"));
			}
			
			List<Map<String,Object>> list = classDetailDAO.queryDB(map);
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
			List<Map<String,Object>> coursesTimeList = coursesTimeDAO.queryDB(new HashMap<String,Object>());
			List<Map<String,Object>> classDetail = classDetailDAO.queryDB(map);
			
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
			map.put("status", "0");
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
//			if(MapUtils.getString(map,"type").equals("0")){
//				map.put("time",comm.timeManage(MapUtils.getString(map,"time"),"-",10));
//			}else{
//				map.put("time",comm.timeManage(MapUtils.getString(map,"time"),"",0));
//			}
//			 classDetailDAO.upsertDB(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String,Object>> queryWeekEvents(Map<String,Object> map) throws Exception {
		List<Map<String,Object>> coursesTimeList = coursesTimeDAO.queryDB(new HashMap<String,Object>());
		map.put("oreder", "time desc,date");
		List<Map<String,Object>> courseDetailList = classDetailDAO.queryDB(map);
		Map<String,Object> session = (Map<String, Object>) this.getSession(true).getAttribute(Context.RS_USER);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		int extendsClassTime = 0;//Ë™≤Â??ãÂ??ÇÈ?
		int extendsClassRanges = 1;//?êË®≠?ΩÊòØ?Æ‰∫∫Ë™?
		int extendsClassStatus = 0;//?êË®≠?ØÁ©∫??
		int countClassRanges = 0;//Ë®àÁ?
		String extendsClassDate = "";
		for (Map<String,Object> courseTimeMap : coursesTimeList) {
			Map<String,Object> weekMap = getWeekObject();
			
			if (MapUtils.getInteger(courseTimeMap, "id") == 7) {
				for (String key : weekMap.keySet()) {
					weekMap.put(key, "3");
				}
			}else {
				for (Map<String,Object> detailMap : courseDetailList) {
					String date = MapUtils.getString(detailMap, "date", "");
					int time = MapUtils.getInteger(detailMap, "time");
					if (StringUtils.equals(date, extendsClassDate) && extendsClassRanges >= 2
							&& extendsClassTime + extendsClassRanges >= time && countClassRanges <= extendsClassRanges) {
						weekMap.put(CommonTools.whatDayIsTheDate(date), extendsClassStatus);
						countClassRanges ++;
						if (countClassRanges == extendsClassRanges) {
							extendsClassTime = 0;//Ë™≤Â??ãÂ??ÇÈ?
							extendsClassRanges = 1;//?êË®≠?ΩÊòØ?Æ‰∫∫Ë™?
							extendsClassStatus = 0;//?êË®≠?ØÁ©∫??
							countClassRanges = 0;
							extendsClassDate = "";
						}
					}
					if (time == MapUtils.getInteger(courseTimeMap, "id")) {
						int ranges = MapUtils.getInteger(detailMap, "ranges");
						int studentId = MapUtils.getInteger(detailMap, "student_id");
						int personId = MapUtils.getInteger(session, "person_id");
						if (studentId == personId) {
							weekMap.put(CommonTools.whatDayIsTheDate(date), "2");
							extendsClassStatus = 2;
						}else {
							weekMap.put(CommonTools.whatDayIsTheDate(date), "1");
							extendsClassStatus = 1;
						}
						if (ranges > 1) {
							extendsClassRanges = ranges;
							extendsClassDate = date;
							extendsClassTime = time;
						}
					}
				}
			}
			weekMap.put("start_time", MapUtils.getString(courseTimeMap, "start_time", ""));
			
			result.add(weekMap);
		}
		
		return result;
	}
	
	private Map<String,Object> getWeekObject() {
		Map<String,Object> result = new HashMap<String,Object>();
		//0?ØÈÅ∏Ôº?Â∑≤ÈÅ∏Ôº??™Â∑±Ôº?‰∏çÂèØ??
		result.put(SUN, "0");
		result.put(MON, "0");
		result.put(TUE, "0");
		result.put(WED, "0");
		result.put(THUS, "0");
		result.put(FRI, "0");
		result.put(SAT, "0");
		
		return result;
	}
	
	@Override
	public List<Map<String,Object>> queryDailyEvents(Map<String,Object> map) throws Exception {
		List<Map<String,Object>> coursesTimeList = coursesTimeDAO.queryDB(new HashMap<String,Object>());
		map.put("oreder", "time desc");
		List<Map<String,Object>> courseDetailList = classDetailDAO.queryDB(map);
		for (Map<String,Object> courseTimeMap : coursesTimeList) {
			courseTimeMap.put("sign", 2);
			
			if (MapUtils.getInteger(courseTimeMap, "id") == 7) {
				courseTimeMap.put("sign", 3);
			}else {
				for (Map<String,Object> detailMap : courseDetailList) {
					int ranges = MapUtils.getInteger(detailMap, "ranges");
					int time = MapUtils.getInteger(detailMap, "time");
					if (time == MapUtils.getInteger(courseTimeMap, "id")) {
						if (MapUtils.getInteger(detailMap, "sign") == 0) {
							courseTimeMap.put("sign", 0);
						}else {
							courseTimeMap.put("sign", 1);
						}
						if (ranges > 1) {
							courseTimeMap.put("ranges", ranges );
						}
					}
					courseTimeMap.put("class_name", MapUtils.getString(detailMap, "class_name") );
				}
			}
		}
		
		return coursesTimeList;
	}
	
	public Map<String,Object> getCurrentPeronalClass() throws Exception {
		Map<String,Object> session = (Map<String, Object>) this.getSession(true).getAttribute(Context.RS_USER);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("student_id", MapUtils.getInteger(session, "person_id"));
		param.put("restMoreThen", "0");
		param.put("e_dateMoreThan", CommonTools.getCurrentDate()); 
		param.put("status", "1");
		List<Map<String, Object>> classMasterQuery = classMasterDAO.queryDB(param);
		if (classMasterQuery.isEmpty()) {
			throw new Exception("Ê≤íÊ??©È?Ë™≤Á?‰∫ÜÔ?Ë´ãÊ¥Ω?çÂ?‰∫∫Âì°");
		}
		Map<String,Object> classMaster = classMasterQuery.get(0);
		
		return classMaster;
	}
	
	@Transactional
	@Override
	public void insertCourse(Map<String,Object> map) throws Exception {
		Map<String,Object> time = new HashMap<String,Object>();
		time.put("start_time",map.get("time"));
		List<Map<String, Object>> coursesTime = coursesTimeDAO.queryDB(time);
		String courseTimeId = MapUtils.getString(coursesTime.get(0), "id");
		Map<String,Object> classMaster = getCurrentPeronalClass();
		insertClassProcess(map, classMaster, courseTimeId);
		
		if (MapUtils.getString(map, "alwaysThisDate", "").equals("true")) {
			String date = MapUtils.getString(map, "date");
			String eClassDate = MapUtils.getString(classMaster, "e_date");
			int rest = MapUtils.getInteger(classMaster, "rest");

			for (int i=0;i<rest;i++) {
				if (determinTheAfter7DateBeforeLinmitDate(date, eClassDate)) {
					date = CommonTools.getAddDayAfterDate(date, 7, "yyyy/MM/dd");
					map.put("date", date);
					insertClassProcess(map, classMaster, courseTimeId);
				}
			}
		}
	}
	
	private void insertClassProcess(Map<String,Object> map,Map<String,Object> classMaster,String courseTimeId) throws Exception {
		map.put("time", courseTimeId);
		map.put("class_master_id", MapUtils.getString(classMaster, "class_master_id"));
		map.put("student_id", MapUtils.getString(classMaster, "student_id"));
		map.put("teacher_id", MapUtils.getString(classMaster, "teacher_id"));
		//?ÆÂ??ΩÊòØ?ã‰∫∫Ë™≤Ô??ΩÊòØ‰∏Ä?ãÊ?ÊÆ?
		map.put("ranges", 1);
		//?ÆÂ??ΩÊòØ?ã‰∫∫Ë™≤Ô?type=0
		map.put("type", 0);
		int count = MapUtils.getInteger(classMaster, "count") + 1;
		int rest = MapUtils.getInteger(classMaster, "summary") - count;
		if (rest > 0) {
			map.put("finish", 0);
		}else {
			map.put("finish", 1);
		}
		map.put("sign", 0);
		classDetailDAO.upsertDB(map);
		
		classMaster.put("count", count);
		classMaster.put("rest", rest);
		classMasterDAO.updateDB(classMaster);
		classMasterHistoryDAO.insertDB(classMaster);
	}
	
	private Boolean determinTheAfter7DateBeforeLinmitDate(String date,String eClassDate) throws ParseException {
		Date selectDate = CommonTools.getStrDateChangeDate(date, "yyyy/MM/dd");
		Date limitDate = CommonTools.getStrDateChangeDate(eClassDate, "yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(selectDate);
		calendar.add(Calendar.DATE, 7);
		Date after7Date = calendar.getTime();
		if (limitDate.after(after7Date)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	@Override
	public void deleteCourse(Map<String,Object> map) throws Exception {
		Map<String,Object> time = new HashMap<String,Object>();
		time.put("start_time", map.get("time"));
		List<Map<String, Object>> coursesTime = coursesTimeDAO.queryDB(time);
		
		Map<String,Object> classMaster = getCurrentPeronalClass();
		if (CommonTools.countNowDifferenceDay(MapUtils.getString(map, "date")) < 2) {
			//Ë®òÈ?
		}
		
		map.put("time",coursesTime.get(0).get("id"));
		List<Map<String, Object>> classDetailList = classDetailDAO.queryDB(map);
		if (classDetailList.isEmpty()) {
			throw new Exception("?æ‰??∞Áõ∏?úË™≤Á®ãÔ?Ë´ãÊ¥Ω?çÂ?‰∫∫Âì°");
		}
		String detailId = MapUtils.getString(classDetailList.get(0), "class_detail_id");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("class_detail_id", detailId);
		classDetailDAO.delete(param);
		if (StringUtils.isNotBlank(MapUtils.getString(classMaster, "day", "")) &&
				StringUtils.isNotBlank(MapUtils.getString(classMaster, "time", ""))) {
			param = new HashMap<String,Object>();
			param.put("class_master_id", MapUtils.getString(classMaster, "class_master_id"));
			param.put("student_id", MapUtils.getString(classMaster, "student_id"));
			param.put("teacher_id", MapUtils.getString(classMaster, "teacher_id"));
			param.put("time", coursesTime.get(0).get("id"));
			param.put("type", 0);
			param.put("finish", 0);
			param.put("sign", 0);
			param.put("ranges", 1);//‰∏ÄÂÆöË??ã‰∫∫Ë™≤Ê??ΩÊîπË™?
			
			List<Map<String, Object>> theLastClassDetailList = classDetailDAO.queryDB(param);
			if (classDetailList.isEmpty()) {
				throw new Exception("?°Ê??¥ÊîπË™≤Á?ÔºåË?Ê¥ΩÊ??ô‰∫∫??);
			}
			while (true) {
				Map<String,Object> newDetail = theLastClassDetailList.get(0);
				newDetail.remove("class_detail_id");
				newDetail.put("date", CommonTools.getAddDayAfterDate(MapUtils.getString(newDetail, "date"), 7, "yyyy/MM/dd"));
				theLastClassDetailList = classDetailDAO.queryDB(newDetail);
				if (classDetailList.isEmpty()) {
					param.put("date", MapUtils.getString(newDetail, "date"));
					classDetailDAO.upsertDB(param);
					break;
				}
			}
		}
		
		classMaster.put("count",MapUtils.getInteger(classMaster, "count") - 1);
		classMaster.put("rest",MapUtils.getInteger(classMaster, "summary") - MapUtils.getInteger(classMaster, "count"));
		classMasterDAO.updateDB(classMaster);
		classMasterHistoryDAO.insertDB(classMaster);
	}
	
	@Override
	public void signCourse(Map<String,Object> map) throws Exception {
		map.put("sign", "1");
		classDetailDAO.updateSign(map);
	}
}
