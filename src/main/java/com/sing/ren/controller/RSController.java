package com.sing.ren.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public abstract class RSController {

	private static Logger logger = Logger.getLogger(RSController.class);
	
	public static final String TREE_STATUS_ADMIN = "Admin";
	public static final String TREE_STATUS_CONTRACT = "Contract";
	public static final String TREE_STATUS_GENERAL = "General";
	
	public static final String MODULE_REPORT = "report";
	public static final String MODULE_CONTRACT = "contractQry";
	public static final String MODULE_ADDITIONAL = "additional";
	public static final String MODULE_DOWNLOAD = "download";
	public static final String MODULE_USER = "user";
	public static final String MODULE_CONTRACT_MGNT = "contractMgnt";
	public static final String MODULE_LICENSE = "license";
	public static final String MODULE_DELIVER = "deliverTranQry";
	public static final String MODULE_DELIVER_MGNT = "deliverTranMgnt";	
	
	public RSController() {
		super();
	}
	
	protected Map<String, Object> parameterMapFromRequest(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		for (String paramName : request.getParameterMap().keySet()) {
			map.put(paramName, request.getParameter(paramName));
		}
		
		return map;
	}
	
	protected static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	protected String successJson() {
		return successJson("success");
	}
	
	@SuppressWarnings("unchecked")
	protected String successJson(String message) {
		JSONObject json = new JSONObject();
		
		json.put("message", message);
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected String exceptionJson(Exception exception) {
		JSONObject json = new JSONObject();
		
		json.put("exception", exception.toString());
		json.put("message", exception.getMessage());
		
		return json.toString();
	}
	
	protected HttpSession getSession(boolean create) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest().getSession(create);
	}
	
	protected Object getDataFromSession(String key) {
        return getSession(true).getAttribute(key);
	}
	
	protected void setDataToSession(String key, Object obj) {
		getSession(true).setAttribute(key, obj);
	}
	
}
