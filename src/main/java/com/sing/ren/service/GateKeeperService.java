package com.sing.ren.service;

import java.util.Map;

public interface GateKeeperService {

	public boolean isUserLogin();
	public void processLogin(Map<String,Object> map) throws Exception;
	public void processLogout() throws Exception;
	public Map<String,Object> getSession() throws Exception;
}
