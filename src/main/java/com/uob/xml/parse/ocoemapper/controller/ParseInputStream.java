package com.uob.xml.parse.ocoemapper.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uob.xml.parse.ocoemapper.model.ExObject;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/v1/parse/" })
@Slf4j
public class ParseInputStream {

	





	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE }) 
	public ResponseEntity<ExObject> postoutputstreamAsObject2(HttpServletRequest request,HttpServletResponse response) throws IOException, ClassNotFoundException {
	  
	  
	        InputStream inputStream = request.getInputStream();
	        byte[] bytedata = StreamUtils.copyToByteArray(inputStream);
	  
	        
	        ByteArrayInputStream bis = new ByteArrayInputStream(bytedata);
	        ObjectInput in = new ObjectInputStream(bis);
	        
			Object obj=in.readObject();
			System.out.println(obj+"clientreq");
			
			ExObject exObject=(ExObject) obj;
			
			System.out.println(exObject+"exObject");
			
			exObject.setName("JAYA");
			exObject.setSecond(35);
			exObject.setThird(3);
			
			///  this code not requied
			ExObject exObject2=new  ExObject();
			
			exObject2=exObject;
				 
			ServletOutputStream servletOut =response.getOutputStream();
			
			ObjectOutputStream oos = new ObjectOutputStream(servletOut);
			oos.writeObject(exObject);
			oos.flush();
			
			//servletOut.write(getByteArray(exObject2));
		//	servletOut.flush();
			
			
			return ResponseEntity.ok(exObject2);
	  
	
	
	  
	  // InputStream is;
	  
	  }
	
	
	
}
