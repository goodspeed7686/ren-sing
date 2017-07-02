package com.sing.ren.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sing.ren.pojo.Person;
import com.sing.ren.service.PersonService;

@Controller
public class PersonController {
	
	@Autowired
	PersonService personService;

	@RequestMapping(value = {"/person"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<Person>> nav(HttpSession session) {
		return new ResponseEntity<List<Person>>(personService.getPerson(), HttpStatus.OK);
	}
	
}
