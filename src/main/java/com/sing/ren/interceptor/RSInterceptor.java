package com.sing.ren.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sing.ren.service.GateKeeperService;


public class RSInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(RSInterceptor.class);
	
	@Autowired
	GateKeeperService gateKeeperService; 
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
		
//		req.getSession().setAttribute("TREE_STATUS", "Admin");
//		
//		Map<String, Object> lmsUser = new HashMap<String, Object>();
//		lmsUser.put("system_group", "1");
//		lmsUser.put("id", "1");
//		lmsUser.put("user_id", "admin");
//		req.getSession().setAttribute(Context.LMS_USER, lmsUser);
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod m = (HandlerMethod)handler;
		String className = m.getBeanType().getName();
		String methodName = m.getMethod().getName();
		
		//過濾不需要登入的項目, 但只允許 localhost 執行
		if (className.equalsIgnoreCase("com.cht.lms.controller.ScheduledJobController")) {
			String userIp = req.getRemoteAddr();
			String remoteHost = req.getRemoteHost();
			
			logger.debug("accessing schedule job controller. remoteHost is " + remoteHost);
			if (remoteHost.equals("0:0:0:0:0:0:0:1") || remoteHost.equals("127.0.0.1")) {
				return true;
			}
		}
		
		boolean isLogin = false;
		
		try {
			isLogin = gateKeeperService.isUserLogin();
			
			if (methodName.equals("showLoginPage") || methodName.equals("processLogin")) {
				if (isLogin) {
					resp.sendRedirect("home");
					return false;
				}
				return true;
			}
			
			if (methodName.equals("processLogout")) {
//				if (!isLogin) {
//					resp.sendRedirect("home");
//					return false;
//				}
				return true;
			}
			
			if (!isLogin) {
				if (className.equals("com.sing.ren.controller.HomeController") && methodName.equals("home")) {
					resp.sendRedirect("logIn");
				} else {
					resp.setStatus(HttpStatus.FORBIDDEN.value());
					resp.getOutputStream().println("使用者未登入或超過閒置時間");
					resp.getOutputStream().flush();
				}
			}
			
			return isLogin;
		} catch (Exception e) {
			logger.error(e);
			
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) {
		
		resp.setHeader("Cache-Control", "no-store");	//HTTP 1.1
		resp.setHeader("Pragma","no-cache"); //HTTP 1.0
		resp.setDateHeader ("Expires", 0);
		
	}
}
