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

import com.sing.ren.common.CommonTools;
import com.sing.ren.pojo.Person;
import com.sing.ren.service.PersonService;

@Controller
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	CommonTools comm=new CommonTools();

	@RequestMapping(value = {"/person"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Map<String,Object>>> nav(HttpSession session) {
		return new ResponseEntity<List<Map<String,Object>>>(personService.getPerson(), HttpStatus.OK);
	}
	@RequestMapping(value = {"/person/query"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(personService.query(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = {"/person/queryForRole"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryForRole(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(personService.queryForRole(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
