package com.sing.ren.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sing.ren.common.CommonTools;
import com.sing.ren.service.CurriculumService;

@Controller
public class CurriculumController {
	
	@Autowired
	CurriculumService curriculumService;
	
	CommonTools comm = new CommonTools();
	
	@RequestMapping(value = {"/curriculum"}, method = RequestMethod.GET)
	public String initCurriculum() {
		return "curriculum/calendar";
	}
	
	@RequestMapping(value = {"/curriculum/popupInsert"}, method = RequestMethod.GET)
	public String popupInsert() {
		return "curriculum/popupInsert";
	}
	
	@RequestMapping(value = {"/curriculum/popupNote"}, method = RequestMethod.GET)
	public String popupNote() {
		return "curriculum/popupNote";
	}
	
	@RequestMapping(value = {"/curriculum/history"}, method = RequestMethod.GET)
	public String history() {
		return "curriculum/popupHistory";
	}
	
	@RequestMapping(value = {"/curriculum/queryDailyCourses"}, method = RequestMethod.GET)
	public String queryDailyCourses() {
		return "curriculum/dailyCourse";
	}
	
	@RequestMapping(value = {"/curriculum/queryWeekCourses"}, method = RequestMethod.GET)
	public String queryWeekCources() {
		return "curriculum/weekCourse";
	}
	
//	@RequestMapping(value = {"/curriculum/insertCourse"}, method = RequestMethod.GET)
//	public String insertCource() {
//		return "curriculum/insertCourse";
//	}

	/**
	 * 查詢學生所有課程
	 * @param session
	 * @param json {'student_id' (必)}
	 * @return
	 */
	@RequestMapping(value = {"/curriculum/query"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(curriculumService.query(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//回傳老師空堂時間
	@RequestMapping(value = {"/curriculum/queryBreak"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryBreak(HttpSession session,@RequestBody String json){
		System.out.println(json);
		try {
			return new ResponseEntity<List<Map<String,Object>>>(curriculumService.queryBreak(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 回傳剩餘課堂數
	 * @param session
	 * @param json {'student_id' (必),
	 * 				'teacher_id',
	 * 				'type'}
	 * @return
	 */
	@RequestMapping(value = {"/curriculum/queryRestClass"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryRestClass(HttpSession session,@RequestBody String json){
		System.out.println(json);
		try {
			return new ResponseEntity<List<Map<String,Object>>>(curriculumService.queryRestClass(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = {"/curriculum/delete"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void detele(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.delete(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/curriculum/update"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.update(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/curriculum/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		try {
			curriculumService.insert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/curriculum/upsert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void upsert(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.upsert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = {"/curriculum/queryWeekEvents"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> queryWeekEvents(HttpSession session,@RequestBody String json) throws Exception{
		Map<String,Object> param = comm.jsonToMap(json);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> header = new HashMap<String,Object>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int currentDate = cal.get(Calendar.DATE); 
		int diffWeek = MapUtils.getInteger(param, "page", 0);
		cal.set(Calendar.DATE, currentDate + diffWeek*7);
		SimpleDateFormat dateStringFormat = new SimpleDateFormat( "MMM, yyyy, w" );
	    String weekTitle = dateStringFormat.format(cal.getTime());
		header.put("weekTitle",weekTitle);
		int[] weekArray = {0,1,2,3,4,5,6};
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int day = cal.get(Calendar.DATE);  
		for (int i=0;i<weekArray.length;i++) {
			int diffDay = weekArray[i] - (dayOfWeek - 1);
			cal.set(Calendar.DATE, day + diffDay);
			dateStringFormat = new SimpleDateFormat( "d" );
		    String weekDate = dateStringFormat.format(cal.getTime());
		    header.put("week" + weekArray[i],weekDate);
		    if (i == 0) {
		    	dateStringFormat = new SimpleDateFormat( "yyyy/MM/dd" );
		    	param.put("date_between_start", dateStringFormat.format(cal.getTime()));
		    }
		    if (i == 6) {
		    	dateStringFormat = new SimpleDateFormat( "yyyy/MM/dd" );
		    	param.put("date_between_end", dateStringFormat.format(cal.getTime()));
		    }
		}
		result.put("header", header);
		result.put("content", curriculumService.queryWeekEvents(param));
		
		return new ResponseEntity<Map<String,Object>> (result, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/curriculum/queryDailyEvents"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> queryDailyEvents(HttpSession session,@RequestBody String json) throws Exception{
		Map<String,Object> param = comm.jsonToMap(json);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> header = new HashMap<String,Object>();
		SimpleDateFormat dateStringFormat = new SimpleDateFormat( "yyyy/MM/dd" );
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int currentDate = cal.get(Calendar.DATE); 
		int diffDay = MapUtils.getInteger(param, "page", 0);
		cal.set(Calendar.DATE, currentDate + diffDay);
		String date = dateStringFormat.format(cal.getTime());
		param.put("date", date);
		dateStringFormat = new SimpleDateFormat( "MMM, yyyy, w" );
		header.put("dayTitle", dateStringFormat.format(cal.getTime()));
		dateStringFormat = new SimpleDateFormat( "d" );
		header.put("date", dateStringFormat.format(cal.getTime()));
		header.put("day", CommonTools.whatDayIsTheDate(date));
		
		result.put("header", header);
		result.put("content", curriculumService.queryDailyEvents(param));
		
		return new ResponseEntity<Map<String,Object>> (result, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/curriculum/insertCourse"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void insertCourse(HttpSession session,@RequestBody String json) throws Exception {
		curriculumService.insertCourse(comm.jsonToMap(json));
	}
	
	@RequestMapping(value = {"/curriculum/deleteCourse"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteCourse(HttpSession session,@RequestBody String json) throws Exception {
		curriculumService.deleteCourse(comm.jsonToMap(json));
	}
	
	@RequestMapping(value = {"/curriculum/signCourse"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void signCourse(HttpSession session,@RequestBody String json) throws Exception {
		curriculumService.signCourse(comm.jsonToMap(json));
	}
}
