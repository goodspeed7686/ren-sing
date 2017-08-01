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
import com.sing.ren.service.MembershipService;

@Controller
public class MembershipController {
	
	@Autowired
	MembershipService membershipService;
	
	CommonTools comm=new CommonTools();
	
	@RequestMapping(value = {"/membership/query"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session){
		Map<String,Object> test=new HashMap<String,Object>();
		return new ResponseEntity<List<Map<String,Object>>>(membershipService.query(test), HttpStatus.OK);
	}
	@RequestMapping(value = {"/membership/delete"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void detele(HttpSession session,@RequestBody String json) {
		try {
			Map<String,Object> map=comm.jsonToMap(json);
			membershipService.delete(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = {"/membership/update"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void update(HttpSession session) {
		membershipService.update(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/membership/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		try {
			Map<String,Object> map=comm.jsonToMap(json);
			membershipService.insert(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = {"/membership/upsert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void upsert(HttpSession session,@RequestBody String json) {
		try {
			Map<String,Object> map=comm.jsonToMap(json);
			membershipService.upsert(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
