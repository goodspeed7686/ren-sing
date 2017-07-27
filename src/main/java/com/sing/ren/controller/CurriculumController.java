package com.sing.ren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
		Map<String,Object> test=new HashMap<String,Object>();
		return new ResponseEntity<List<Map<String,Object>>>(curriculumService.query(test), HttpStatus.OK);
	}
	@RequestMapping(value = {"/curriculum/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void detele(HttpSession session) {
		curriculumService.delete(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/update"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void update(HttpSession session) {
		curriculumService.update(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		List<Map<String,Object>> mm = null;
		Map<String,Object> m2=null;
		try {
	//		mm=new CommonTools().jsonToList(json);
			m2=new CommonTools().jsonToMap(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		for (Object key : m2.keySet()) {
//			System.out.println(key + " = " + m2.get(key));
//		}
		Map<String,Object> test=new HashMap<String,Object>();
		test.put("classMastertId",1111);
		curriculumService.insert(m2);
	}
	@RequestMapping(value = {"/curriculum/upsert"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void upsert(HttpSession session) {
		curriculumService.upsert(new HashMap<String,Object>());
	}
}
