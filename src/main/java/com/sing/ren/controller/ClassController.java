package com.sing.ren.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sing.ren.common.CommonTools;
import com.sing.ren.service.ClassService;

@Controller
public class ClassController {
	@Autowired
	ClassService classService;
	
	CommonTools comm=new CommonTools();
	
	@RequestMapping(value = {"/class"}, method = RequestMethod.GET)
	public String initHome() {
		return "class/classList";
	}
	
	@RequestMapping(value = {"/classDetail"}, method = RequestMethod.GET)
	public String classDetail() {
		return "class/classDetailList";
	}
	
	@RequestMapping(value = {"/addClass"}, method = RequestMethod.GET)
	public String addClass() {
		return "class/addClass";
	}
	
	@RequestMapping(value = {"/addClassDetail"}, method = RequestMethod.GET)
	public String addClassDetail() {
		return "class/addClassDetail";
	}
	
	@RequestMapping(value = {"/class/query"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(classService.query(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = {"/class/queryDetail"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryDetail(HttpSession session,@RequestBody String json) throws Exception{
		return new ResponseEntity<List<Map<String,Object>>>(classService.queryDetail(comm.jsonToMap(json)), HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/class/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void insert(HttpSession session,@RequestBody String json) {
		try {
			classService.insert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/class/update"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(HttpSession session,@RequestBody String json) {
		try {
			classService.update(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/class/queryCount"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Map<String,Object>> queryCount(HttpSession session,@RequestBody String json) throws Exception {
		return new ResponseEntity<Map<String,Object>>(classService.queryMasterCount(comm.jsonToMap(json)), HttpStatus.OK);
	}
}
