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
import com.sing.ren.service.ComPropertiesService;

@Controller
public class ComPropertiesController {
	
	@Autowired
	ComPropertiesService comPropertiesService;
	
	CommonTools comm=new CommonTools();
	
	@RequestMapping(value = {"/properties"}, method = RequestMethod.GET)
	public String initHome() {
		return "properties/propertiesList";
	}
	
	
	@RequestMapping(value = {"/comProperties/classType"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryClassType(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(comPropertiesService.queryClassType(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = {"/comProperties/classLevel"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryClassLevel(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(comPropertiesService.queryClassLevel(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = {"/comProperties/classPlace"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryClassPlace(HttpSession session,@RequestBody String json){
		try {
			return new ResponseEntity<List<Map<String,Object>>>(comPropertiesService.queryClassPlace(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
