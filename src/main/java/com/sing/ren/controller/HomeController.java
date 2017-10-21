package com.sing.ren.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sing.ren.common.CommonTools;
import com.sing.ren.service.GateKeeperService;

@Controller
public class HomeController extends RSController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	CommonTools comm = new CommonTools();
	
	@Autowired
	GateKeeperService geteKeeper;
	
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = {"/pets"}, method = RequestMethod.GET)
	public String pets() {
		return "pets";
	}
	
	@RequestMapping(value = {"/alert"}, method = RequestMethod.GET)
	public String alert() {
		return "alert";
	}
	
	@RequestMapping(value = "/menu", method = {RequestMethod.GET, RequestMethod.POST})
	public String nav(HttpSession session) {
		
		logger.debug("TREE_STATUS " + session.getAttribute("TREE_STATUS"));
		
		return "menu";
	}
	
	@RequestMapping(value = "/logIn", method = {RequestMethod.GET, RequestMethod.POST})
	public String showLoginPage() {
		return "logIn";
	}
	
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public String processLogin(@RequestBody String json) throws Exception {
		
		try {
			geteKeeper.processLogin(comm.jsonToMap(json));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return e.getMessage();
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String processLogout() throws Exception {
		geteKeeper.processLogout();
		return "home";
	}

	@RequestMapping(value = {"/session"}, method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Map<String,Object>> session(HttpSession session,@RequestBody String json) {
		return new ResponseEntity<Map<String,Object>>(geteKeeper.getSession(), HttpStatus.OK);
	}
}
