package com.sing.ren.controller;

import java.util.List;
import java.util.Map;

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

	@RequestMapping(value = {"/master"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Map<String,Object>>> nav(HttpSession session) {
		return new ResponseEntity<List<Map<String,Object>>>(masterService.getMaster(), HttpStatus.OK);
	}
	
}
