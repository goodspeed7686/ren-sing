package com.sing.ren.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sing.ren.Context;
import com.sing.ren.dao.table.AccountDAO;
import com.sing.ren.dao.table.PersonDAO;
import com.sing.ren.service.GateKeeperService;
import com.sing.ren.service.RSService;
import com.sing.ren.service.security.AccountPermissionType;

@Service
public class GateKeeperServiceImpl extends RSService implements GateKeeperService {

	private static final Logger logger = LoggerFactory.getLogger(GateKeeperServiceImpl.class);
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	PersonDAO personDAO;
	
	@Override
	public boolean isUserLogin() {
		return super.isLogin();
	}

	@Override
	public void processLogin(Map<String,Object> map) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("user_id", userId);
////		map.put("pwd", DigestUtils.md5Hex(pwd));
//		map.put("pwd", pwd);
		
		logger.debug(map.toString());
		
		Map<String, Object> user = null;
		try {
			
			List<Map<String, Object>> userList = accountDAO.queryDB(map);
			
			if (userList.size() > 0)
				//假設資料庫不會有重複帳號也不應該有
				user = userList.get(0);
			else
				throw new Exception("帳號或密碼錯誤");
			
			if(user == null){
				//帳號：ｘｘｘｘｘ不存在，無權使用本系統！
				throw new Exception("msg.E019");
			}
			
			String status = MapUtils.getString(user, "status", "");
			if (StringUtils.isEmpty(status)) {
				throw new Exception("用戶資料錯誤 (status is empty)");
			}
			
			if(status.equals(Context.SYSTEM_ID_SET_INACTIVE)) {
				//帳號：ｘｘｘｘｘ已停用，無權使用本系統!
				throw new Exception("msg.E020");
			}
			
			logger.debug("user:"+user);
			//TODO session create time
			user.put("login_time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			
			logger.info("user " + user.get("user_id") + " logged in.");
		}catch (Exception e) {
//			e.printStackTrace();
//			logger.debug(e.getMessage());
			throw e;
		}
		
		this.setDataToSession(Context.RS_USER, user);
		
		AccountPermissionType accountPermissionType = AccountPermissionType.valueOf(Integer.valueOf(user.get("role").toString()));
		logger.debug("accountPermissionType:"+accountPermissionType.getType());
		
//		List<String> menuItems = new ArrayList<String>();
//		if (accountPermissionType.isAdmin()) {
//			this.setDataToSession(Context.TREE_STATUS, "Admin");
//			
//			menuItems.add(RSController.MODULE_REPORT);
//			menuItems.add(RSController.MODULE_CONTRACT);
//			menuItems.add(RSController.MODULE_DELIVER);
//			menuItems.add(RSController.MODULE_ADDITIONAL);
//			menuItems.add(RSController.MODULE_DOWNLOAD);
//			menuItems.add(RSController.MODULE_USER);
//			menuItems.add(RSController.MODULE_CONTRACT_MGNT);
//			menuItems.add(RSController.MODULE_LICENSE);
//		} else if (accountPermissionType.isContract()) {
//			this.setDataToSession(Context.TREE_STATUS, "Contract");
//			
//			menuItems.add(RSController.MODULE_REPORT);
//			menuItems.add(RSController.MODULE_CONTRACT);
//			menuItems.add(RSController.MODULE_DELIVER);
//			menuItems.add(RSController.MODULE_ADDITIONAL);
//			menuItems.add(RSController.MODULE_DOWNLOAD);
//			menuItems.add(RSController.MODULE_USER);
//			menuItems.add(RSController.MODULE_CONTRACT_MGNT);
//			menuItems.add(RSController.MODULE_LICENSE);
//		} else {
//			this.setDataToSession(Context.TREE_STATUS, "General");
//			
//			menuItems.add(RSController.MODULE_REPORT);
//			menuItems.add(RSController.MODULE_CONTRACT);
//			menuItems.add(RSController.MODULE_DELIVER);
//			menuItems.add(RSController.MODULE_ADDITIONAL);
//			menuItems.add(RSController.MODULE_DOWNLOAD);
//			menuItems.add(RSController.MODULE_USER);
//		}
//		this.setDataToSession("menuItems", menuItems);
	}

	@Override
	public void processLogout() throws Exception {
		Object user = this.getDataFromSession(Context.RS_USER);
		if (user != null) {
			logger.debug("clean session");
			this.setDataToSession(Context.RS_USER, null);
		}
	}

	@Override
	public Map<String,Object> getSession() throws Exception {
		Map<String,Object> session = (Map<String, Object>) this.getSession(true).getAttribute(Context.RS_USER);
		session.remove("password");
		session.remove("login_time");
		session.remove("status");
		Map<String,Object> result = personDAO.queryOne(session);
		session.put("name", result.get("name"));
		
		return session;
	}
}
