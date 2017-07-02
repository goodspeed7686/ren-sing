//package com.sing.ren.service.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.sing.ren.Context;
//import com.sing.ren.service.GateKeeperService;
//import com.sing.ren.service.LoginService;
//import com.sing.ren.service.RSService;
//import com.sing.ren.service.security.AccountPermissionType;
//
//@Service
//public class GateKeeperServiceImpl extends RSService implements GateKeeperService {
//
//	private static final Logger logger = LoggerFactory.getLogger(GateKeeperServiceImpl.class);
//	
//	@Autowired
//	LoginService loginService;
//	
//	@Override
//	public boolean isUserLogin() {
//		return super.isLogin();
//	}
//
//	@Override
//	public void processLogin(String userId, String pwd, ModelAndView mav) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("user_id", userId);
//		map.put("pwd", DigestUtils.md5Hex(pwd));
//		
//		logger.debug(map.toString());
//		
//		Map<String, Object> user = null;
//		try {
//			user = loginService.processLogin(map);
//		}catch (Exception e) {
//			String msg = getMessage(e.getMessage().trim());
//			if (StringUtils.isEmpty(msg)) {
//				msg = e.getMessage();
//			}
//			
//			throw new Exception(msg);
//		}
//		
//		this.setDataToSession(Context.RS_USER, user);
//		
//		AccountPermissionType accountPermissionType = AccountPermissionType.valueOf(Integer.valueOf(user.get("system_group").toString()));
//		logger.debug("accountPermissionType:"+accountPermissionType.getType());
//		
////		List<String> menuItems = new ArrayList<String>();
////		if (accountPermissionType.isAdmin()) {
////			this.setDataToSession(Context.TREE_STATUS, "Admin");
////			
////			menuItems.add(RSController.MODULE_REPORT);
////			menuItems.add(RSController.MODULE_CONTRACT);
////			menuItems.add(RSController.MODULE_DELIVER);
////			menuItems.add(RSController.MODULE_ADDITIONAL);
////			menuItems.add(RSController.MODULE_DOWNLOAD);
////			menuItems.add(RSController.MODULE_USER);
////			menuItems.add(RSController.MODULE_CONTRACT_MGNT);
////			menuItems.add(RSController.MODULE_LICENSE);
////		} else if (accountPermissionType.isContract()) {
////			this.setDataToSession(Context.TREE_STATUS, "Contract");
////			
////			menuItems.add(RSController.MODULE_REPORT);
////			menuItems.add(RSController.MODULE_CONTRACT);
////			menuItems.add(RSController.MODULE_DELIVER);
////			menuItems.add(RSController.MODULE_ADDITIONAL);
////			menuItems.add(RSController.MODULE_DOWNLOAD);
////			menuItems.add(RSController.MODULE_USER);
////			menuItems.add(RSController.MODULE_CONTRACT_MGNT);
////			menuItems.add(RSController.MODULE_LICENSE);
////		} else {
////			this.setDataToSession(Context.TREE_STATUS, "General");
////			
////			menuItems.add(RSController.MODULE_REPORT);
////			menuItems.add(RSController.MODULE_CONTRACT);
////			menuItems.add(RSController.MODULE_DELIVER);
////			menuItems.add(RSController.MODULE_ADDITIONAL);
////			menuItems.add(RSController.MODULE_DOWNLOAD);
////			menuItems.add(RSController.MODULE_USER);
////		}
////		this.setDataToSession("menuItems", menuItems);
//	}
//
//	@Override
//	public void processLogout() throws Exception {
//		Object user = this.getDataFromSession(Context.RS_USER);
//		if (user != null) {
//			logger.debug("clean session");
//			this.setDataToSession(Context.RS_USER, null);
//		}
//	}
//}
