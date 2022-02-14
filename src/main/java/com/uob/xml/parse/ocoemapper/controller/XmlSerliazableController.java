package com.uob.xml.parse.ocoemapper.controller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uob.xml.parse.ocoemapper.model.Person;
import com.uob.xml.parse.ocoemapper.model.Root;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = { "/v1/root/" })
@Slf4j
public class XmlSerliazableController {
	
	@Autowired
	private  XmlMapper mapper;
	
	@GetMapping("/getFirstElement")
	public String getFirstElement() throws Exception {

		//return userService.getRSAKey(userJksPath, jkspassword, alias);

		// System.out.println("token"+token);

		// return new JwtResponse(token);
		
//ObjectMapper xmlMapper= new XmlMapper();
		
		Root per=mapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(Paths.get("./src/main/resources/Root.xml")),StandardCharsets.UTF_8), Root.class);
		System.out.println(per.getAnotherroot().getSecondElement().get(1).getSecondFirst());
		return per.getFirstElement();
		
	}
	
	
	@GetMapping("/getPersonData")
	public String getFirstElement2() throws Exception {

		//return userService.getRSAKey(userJksPath, jkspassword, alias);

		// System.out.println("token"+token);

		// return new JwtResponse(token);
		
//ObjectMapper xmlMapper= new XmlMapper();
		
		Person per=mapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(Paths.get("./src/main/resources/student.xml")),StandardCharsets.UTF_8), Person.class);
		//System.out.println(per.getAnotherroot().getSecondElement().get(1).getSecondFirst());
		return per.getFirstName();
		
	}
	
	

}
