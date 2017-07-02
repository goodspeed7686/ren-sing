package com.sing.ren.service;

import org.springframework.web.servlet.ModelAndView;


public interface GateKeeperService {

	public boolean isUserLogin();
	public void processLogin(String userId, String pwd, ModelAndView mav) throws Exception;
	public void processLogout() throws Exception;
}
