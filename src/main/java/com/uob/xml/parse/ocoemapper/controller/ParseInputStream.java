package com.uob.xml.parse.ocoemapper.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uob.xml.parse.ocoemapper.model.ExObject;
import com.uob.xml.parse.ocoemapper.model.Root;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/v1/parse/" })
@Slf4j
public class ParseInputStream {
	
	
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/writeresposoutputstream")
	public ResponseEntity<String> postoutputstream(HttpServletRequest httpServletRequest) {

		

		InputStream is;
		try {
			is = httpServletRequest.getInputStream();
		 
		byte[] bytes = StreamUtils.copyToByteArray(is);
         String output=new String(bytes);
          
        //  JwtRequest jwtreq = mapper.readValue((new String(bytes)), JwtRequest.class);
	//	Object obj = convertBytesToObject(bytes);
		return ResponseEntity.ok(output);
		
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().body("EXCEPTION"+e.getMessage());
		}

	}

	@PostMapping("/writeresposoutputstreamAsObject")
	public ResponseEntity<Object> postoutputstreamAsObject(HttpServletRequest httpServletRequest,Object obj) {

		

		InputStream is;
		try {
			is = httpServletRequest.getInputStream();
		 
		byte[] bytes = StreamUtils.copyToByteArray(is);
         String output=new String(bytes);
         
         obj=mapper.readValue(output, Object.class);
          
        //  JwtRequest jwtreq = mapper.readValue((new String(bytes)), JwtRequest.class);
	//	Object obj = convertBytesToObject(bytes);
		return ResponseEntity.ok(obj);
		
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().body("EXCEPTION"+e.getMessage());
		}

	}
	
	
	@PostMapping("/writeresposoutputstreamAsCreatedObject")
	public ResponseEntity<Object> postoutputstreamAsObject(HttpServletRequest httpServletRequest,ExObject obj) {

		

		InputStream is;
		try {
			is = httpServletRequest.getInputStream();
		 
		byte[] bytes = StreamUtils.copyToByteArray(is);
         String output=new String(bytes);
         
         obj=mapper.readValue(output, ExObject.class);
          
        //  JwtRequest jwtreq = mapper.readValue((new String(bytes)), JwtRequest.class);
	//	Object obj = convertBytesToObject(bytes);
		return ResponseEntity.ok(obj);
		
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ResponseEntity.badRequest().body("EXCEPTION"+e.getMessage());
		}

	}
	

}
