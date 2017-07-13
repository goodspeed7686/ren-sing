package com.sing.ren.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sing.ren.pojo.ClassMaster;
import com.sing.ren.service.MasterService;

@Controller
public class MasterController {
	
	@Autowired
	MasterService masterService;

	@RequestMapping(value = {"/master/query"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<ClassMaster>> nav(HttpSession session) {
		return new ResponseEntity<List<ClassMaster>>(masterService.getMaster(), HttpStatus.OK);
	}
	
}
