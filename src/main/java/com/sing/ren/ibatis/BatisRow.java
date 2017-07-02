package com.sing.ren.ibatis;

import java.util.Map;

public class BatisRow {

	private Map<String, Object> map;
	
	public BatisRow(Map<String, Object> map) {
		super();
		
		this.map = map;
	}
	
	public Integer getInt(String key) {
		return (Integer)safeGet(key);
	}
	
	public String getString(String key) {
		return (String)safeGet(key);
	}
	
	private Object safeGet(String key) {
		Object nullable = map.get(key);
		if (null == nullable) {
			return "0";
		}
		
		return nullable;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String key : this.map.keySet()) {
			sb.append(key + "=" + map.get(key));
		}
		
		return sb.toString();
	}
}
