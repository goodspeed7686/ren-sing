package com.sing.ren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sing.ren.common.CommonTools;
import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.service.CurriculumService;

@Controller
public class CurriculumController {
	
	@Autowired
	CurriculumService curriculumService;

	@RequestMapping(value = {"/curriculum/query"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session){
		HashMap mm=new HashMap<String,Object>();
	//	mm.put("classMasterId",1112);
		Map<String,Object> test=new HashMap<String,Object>();
		test.put("class_master_id",1111);
		curriculumService.insert(test);
		return new ResponseEntity<List<Map<String,Object>>>(curriculumService.query(mm), HttpStatus.OK);
	}
	@RequestMapping(value = {"/curriculum/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void detele(HttpSession session) {
		ClassDetail dd=new ClassDetail();
		dd.setClassDetailId(3);
		curriculumService.delete(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/update"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void update(HttpSession session) {
		ClassDetail dd=new ClassDetail();
		dd.setClassDetailId(3);
		dd.setClassMasterId(1112);
		dd.setSong("鬥陣update");
		dd.setTeacherId("1235645456");
		dd.setStudentId("123564545");
		dd.setDate("2009/09/09");
		dd.setUpdateTime("0");
		dd.setUpdater("小法師");
		dd.setStudentNote("");
		dd.setHw("大便庫子上");
		dd.setTime("15:55");
		curriculumService.update(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		List<Map<String,Object>> mm = null;
		Map<String,Object> m2=null;
		try {
			mm=new CommonTools().jsonToList(json);
			m2=new CommonTools().jsonToMap(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Object key : m2.keySet()) {
            System.out.println(key + " = " + m2.get(key));
        }
//		ClassDetail dd=new ClassDetail();
//		dd.setClassDetailId(3);
//		dd.setClassMasterId(1112);
//		dd.setSong("insert");
//		dd.setTeacherId("1235645456");
//		dd.setStudentId("123564545");
//		dd.setDate("2009/09/09");
//		dd.setUpdateTime("0");
//		dd.setUpdater("0");
//		dd.setStudentNote("");
//		dd.setHw("大便庫子上");
//		dd.setTime("15:55");
		Map<String,Object> test=new HashMap<String,Object>();
		test.put("classMastertId",1111);
		curriculumService.insert(m2);
	}
	@RequestMapping(value = {"/curriculum/upsert"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void upsert(HttpSession session) {
		ClassDetail dd=new ClassDetail();
		dd.setClassDetailId(3);
		dd.setClassMasterId(1112);
		dd.setSong("小水滴5225");
		dd.setTeacherId("1235645456");
		dd.setStudentId("123564545");
		dd.setDate("2009/09/09");
		dd.setUpdateTime("0");
		dd.setUpdater("0");
		dd.setStudentNote("");
		dd.setHw("大便庫子上");
		dd.setTime("15:55");
		curriculumService.upsert(new HashMap<String,Object>());
	}
}
