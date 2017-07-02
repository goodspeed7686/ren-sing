package com.sing.ren.service;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sing.ren.Context;
import com.sing.ren.common.CommonTools;
import com.sing.ren.pojo.RSUser;


public class RSService {

	private static Logger logger = Logger.getLogger(RSService.class);
	
	private static final String RS_SYSTEM_DIR = "/usr/ren-sing/";
	
	private static final String REPORT_FILE = "file/";
	private static final String REPORT_DIR = "report/";
	
	private static final String tmpFolderPath = "/tmp/ren-sing/";
	
	public static final String MIME_EXCEL = "application/application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	@Autowired
	private HttpServletRequest request;	
	@Autowired
    private ResourceBundleMessageSource messageSource;
	
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, null);
	}
	
	public void nullCheck(Map paramMap, String[] names) throws IllegalArgumentException {
		for (String key : names) {
			if (!paramMap.containsKey(key)) {
				throw new IllegalArgumentException(key + " required.");
			} else if (StringUtils.isEmpty(paramMap.get(key).toString())) {
				throw new IllegalArgumentException(key + " required.");
			}
		}
	}
	
	public HttpSession getSession(boolean create) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest().getSession(create);
	}
	
	public Object getDataFromSession(String key, Object defaultValue) {
		
        Object value = getSession(true).getAttribute(key);
        
        return (null != value) ? value : defaultValue;
	}
	
	public Object getDataFromSession(String key) {
		return getDataFromSession(key, "");
	}
	
	public void setDataToSession(String key, Object obj) {
		getSession(true).setAttribute(key, obj);
	}
	
	public void removeDataInSession(String key) {
		HttpSession session = getSession(false);
		if (null != session) {
			session.removeAttribute(key);
		}
	}
	
	public boolean isLogin() {
		HttpSession session = this.getSession(false);
		
		if (null == session) {
			return false;
		}
		
		Map<String, Object> user = (Map<String, Object>)session.getAttribute(Context.RS_USER);
		if (null == user) {
			return false;
		}
		
		return true;
	}
	
	public Object nullSafe(Object o) {
		if (null == o) {
			o = new Object();
		}
		
		return o;
	}
	
	public Object getParam(Map<String, Object> paramMap, String key, Object defaultValue) {
		if (!paramMap.containsKey(key)) {
			return defaultValue;
		}
		
		Object o = paramMap.get(key);
		if (StringUtils.isEmpty(o.toString())) {
			return defaultValue;
		}
		
		return paramMap.get(key);
 	}
	
	public void processQueryString(Map<String, Object> map){
		String database_type = map.containsKey("database_type")?map.get("database_type").toString():"";
		for(String key:map.keySet()){
			String queryStr=map.get(key).toString();
			if(StringUtils.indexOf(queryStr, "'") >= 0){
				if(StringUtils.isNotBlank(database_type) && database_type =="oracle"){
					//處理oracle的資料庫
					map.put(key, StringUtils.replace(queryStr, "'", "''"));
				}else{
					map.put(key, StringUtils.replace(queryStr, "'", "\\'"));
				}
			}
		}
	}
	
	protected void whoAndWhen(Map<String, Object> paramMap) {
		paramMap.put("updater", ((Map) this.getDataFromSession(Context.RS_USER)).get("user_id"));
		paramMap.put("update_time", CommonTools.getCurrentDateTime());
	}
	
	protected String lmsSystemDirPrefix() {
		return RS_SYSTEM_DIR;
	}
	
	protected String downloadReportPath() {
		return lmsSystemDirPrefix() + REPORT_FILE + REPORT_DIR;
	}
	
	protected String tmpfileUploadPath() {
		return tmpFolderPath;
	}
	
	protected void checkAndCreateTmpUploadFolder() throws Exception {
		File outFolder = new File( tmpfileUploadPath() );
		if (!outFolder.exists()) {
			logger.info( String.format("上傳檔案暫存資料夾不存在, 重新建立(%s)", tmpfileUploadPath()) );
			FileUtils.forceMkdir(outFolder);
			logger.info( String.format("上傳檔案暫存資料已建立(%s)", tmpfileUploadPath()) );
		}
	}
	
	protected RSUser rsUser() {
		return new RSUser();
	}
}
