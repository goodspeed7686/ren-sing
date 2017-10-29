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
import com.sing.ren.service.MasterService;

@Controller
public class ClassController {
	@Autowired
	MasterService masterService;
	
	CommonTools comm=new CommonTools();
	
	@RequestMapping(value = {"/class"}, method = RequestMethod.GET)
	public String initHome() {
		return "class/classList";
	}
	
	@RequestMapping(value = {"/addClass"}, method = RequestMethod.GET)
	public String addClass() {
		return "class/addClass";
	}
	
	@RequestMapping(value = {"/class/query"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(masterService.query(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = {"/class/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void insert(HttpSession session,@RequestBody String json) {
	//	System.out.println(json);
		try {
			masterService.insert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
