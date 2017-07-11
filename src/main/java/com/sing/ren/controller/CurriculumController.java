package com.sing.ren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sing.ren.pojo.ClassDetail;
import com.sing.ren.service.CurriculumService;

@Controller
public class CurriculumController {
	
	@Autowired
	CurriculumService curriculumService;

	@RequestMapping(value = {"/curriculum/query"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<ClassDetail>> query(HttpSession session) {
		HashMap mm=new HashMap<String,Object>();
		mm.put("classMasterId",1112);
		ClassDetail dd=new ClassDetail();
		dd.setClassMasterId(1112);
		return new ResponseEntity<List<ClassDetail>>(curriculumService.getDetail(dd), HttpStatus.OK);
	}
	@RequestMapping(value = {"/curriculum/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void detele(HttpSession session) {
		
		curriculumService.deleteDetail(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/update"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void update(HttpSession session) {
		
		curriculumService.updateDetail(new HashMap<String,Object>());
	}
	@RequestMapping(value = {"/curriculum/insert"}, method = {RequestMethod.GET, RequestMethod.POST})
	public void insert(HttpSession session) {
		ClassDetail dd=new ClassDetail();
		dd.setClassDetailId(3);
		dd.setClassMasterId(1112);
		dd.setSong("鬥陣取");
		dd.setTeacherId("1235645456");
		dd.setStudentId("123564545");
		dd.setDate("2009/09/09");
		dd.setUpdateTime("0");
		dd.setUpdater("0");
		dd.setStudentNote("");
		dd.setHw("");
		dd.setTime("15:55");
		curriculumService.insertDetail(dd);
	}
	
}
