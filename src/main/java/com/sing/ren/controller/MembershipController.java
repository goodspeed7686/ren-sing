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
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sing.ren.common.CommonTools;
import com.sing.ren.service.MembershipService;

@Controller
public class MembershipController {
	
	@Autowired
	MembershipService membershipService;
	
	CommonTools comm=new CommonTools();
	
	@RequestMapping(value = {"/membership"}, method = RequestMethod.GET)
	public String initHome() {
		return "membership/memList";
	}
	
	@RequestMapping(value = {"/addMem"}, method = RequestMethod.GET)
	public String addMembership() {
		return "membership/addMem";
	}
	
	@RequestMapping(value = {"/membership/query"}, method = {RequestMethod.GET, RequestMethod.POST,}, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> query(HttpSession session,@RequestBody String json) throws ParseException{
		return new ResponseEntity<List<Map<String,Object>>>(membershipService.query(comm.jsonToMap(json)), HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/membership/queryPerson"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Map<String,Object>> queryPerson(HttpSession session,@RequestBody String json) throws ParseException{
		return new ResponseEntity<Map<String,Object>>(membershipService.queryPerson(comm.jsonToMap(json)), HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/membership/delete"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void detele(HttpSession session,@RequestBody String json) {
		try {
			membershipService.delete(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/membership/update"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void update(HttpSession session,@RequestBody String json) {
		try {
			membershipService.update(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/membership/insert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void insert(HttpSession session,@RequestBody String json) {
		System.out.println(json);
		try {
			membershipService.insert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/membership/upsert"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void upsert(HttpSession session,@RequestBody String json) {
		try {
			membershipService.upsert(comm.jsonToMap(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/membership/validateAccount"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Boolean validateAccount(HttpSession session,@RequestBody String json) throws ParseException{
		List<Map<String,Object>> account=membershipService.queryAccount(comm.jsonToMap(json));
		if(account == null) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(value = {"/membership/queryMemAccount"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> queryMemAccount(HttpSession session,@RequestBody String json) throws ParseException{
		return new ResponseEntity<List<Map<String,Object>>>(membershipService.queryAccount(comm.jsonToMap(json)), HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/membership/getNewAcc"}, method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getNewAcc(HttpSession session,@RequestBody String json) throws ParseException{
		try {
			return new ResponseEntity<Map<String,Object>>(membershipService.getNewAcc(comm.jsonToMap(json)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
