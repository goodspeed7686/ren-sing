package com.sing.ren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClassController {

	@RequestMapping(value = {"/class"}, method = RequestMethod.GET)
	public String initHome() {
	//	return "class/addClass";
		return "class/classList";
	}
	
	@RequestMapping(value = {"/addClass"}, method = RequestMethod.GET)
	public String addClass() {
	//	return "class/addClass";
		return "class/addClass";
	}
}
