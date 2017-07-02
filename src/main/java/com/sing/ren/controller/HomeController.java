package com.sing.ren.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sing.ren.service.GateKeeperService;

@Controller
public class HomeController extends RSController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
//	GateKeeperService geteKeeper;
	
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String initHome() {
		return "home";
	}
	
	@RequestMapping(value = "/menu", method = {RequestMethod.GET, RequestMethod.POST})
	public String nav(HttpSession session) {
		
		logger.debug("TREE_STATUS " + session.getAttribute("TREE_STATUS"));
		
		return "menu";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showLoginPage(ModelAndView ma) {
		
		ma.setViewName("login");
		
		return ma;
	}
	
//	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
//	public ModelAndView processLogin(
//			@RequestParam(value = "userId", required=true, defaultValue="") String userId,
//			@RequestParam(value = "pwd", required=true, defaultValue="") String pwd,
//			ModelAndView mav) throws Exception {
//		
//		try {
//			geteKeeper.processLogin(userId, pwd, mav);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			
//			mav.addObject("showLoginPopup", true);
//			
//			return showLoginPage(mav);
//		}
//		
//		return home(mav);
//	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView processLogout(ModelAndView ma) throws Exception {
		
//		geteKeeper.processLogout();
		
		return showLoginPage(ma);
	}

}
