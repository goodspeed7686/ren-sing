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
import com.sing.ren.service.CurriculumService;

@Controller
public class CurriculumController {
	
	@Autowired
	CurriculumService curriculumService;
	
	CommonTools comm=new CommonTools();

	@RequestMapping(value = {"/curriculum/query"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session){
		Map<String,Object> test=new HashMap<String,Object>();
		return new ResponseEntity<List<Map<String,Object>>>(curriculumService.query(test), HttpStatus.OK);
	}
	@RequestMapping(value = {"/curriculum/delete"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void detele(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.delete(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = {"/curriculum/update"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void update(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.update(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = {"/curriculum/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		try {
			curriculumService.insert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = {"/curriculum/upsert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void upsert(HttpSession session,@RequestBody String json) {
		try {
			curriculumService.upsert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
