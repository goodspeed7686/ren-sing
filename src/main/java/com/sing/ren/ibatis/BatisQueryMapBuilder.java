package com.sing.ren.ibatis;

import java.util.HashMap;
import java.util.Map;

public class BatisQueryMapBuilder {

	private Map<String, Object> map;
	
	public BatisQueryMapBuilder() {
		super();
		
		this.map = new HashMap<String, Object>();
	}
	
	public BatisQueryMapBuilder add(String key, Object value) {
		this.map.put(key, value);
		
		return this;
	}
	
	public BatisQueryMapBuilder addOrder(String key, Object value) {
		return add(key, value);
	}
	
	public BatisQueryMapBuilder offset(int offset) {
		if (offset != 0) {
			return add("offset", offset);
		} else {
			return this;
		}
	}
	
	public BatisQueryMapBuilder limit(int limit) {
		if (limit != 0) {
			return add("limit", limit);
		} else {
			return this;
		}
	}
	
	public Map<String, Object> build() {
		return this.map;
	}
	
	public static Map<String, Object> emptyParamMap() {
		return new HashMap<String, Object>();
	}
}
