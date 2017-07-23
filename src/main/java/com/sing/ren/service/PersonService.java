package com.sing.ren.service;

import java.util.List;
import java.util.Map;

import com.sing.ren.exception.RSServiceException;
import com.sing.ren.pojo.Person;

public interface PersonService {

	public List<Map<String,Object>> getPerson();
	
}
