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

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Gson gson;





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
	
	
	private static byte[] getByteArray(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
			os.writeObject(obj);
		}
		return bos.toByteArray();
	}

	@RequestMapping(value = "/postObjectvalue", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<ExObject> postoutputstreamAsObject122(@RequestParam Map<String, Object> allParams,
			HttpServletRequest httpServletRequest) throws ClassNotFoundException, IOException {

		// System.out.println(allParams+"allParams");
		// System.out.println(allParams + "allParams");

		Object a = allParams.get("sampleobject");

		ExObject outputMovie = gson.fromJson(allParams.get("sampleobject").toString(), ExObject.class);

		System.out.println(outputMovie);

		outputMovie.setName("JAYA");
		outputMovie.setSecond(35);
		outputMovie.setThird(3);

		// This code doesnot work

		InputStream is = httpServletRequest.getInputStream();
		byte[] bytes = StreamUtils.copyToByteArray(is);
		// commented getting exception here line 190 -to 192

		/*
		 * ByteArrayInputStream bi = new ByteArrayInputStream(bytes); ObjectInputStream
		 * oi = new ObjectInputStream(bi); Object obj = oi.readObject();
		 * System.out.println("obj" + obj);
		 */

		// same code work for client and not her

		Reader in = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "UTF-8"));

		for (int c; (c = in.read()) >= 0;)
			System.out.print((char) c);

		// ByteArrayInputStream bi1 = new
		// ByteArrayInputStream(allParams.get("sampleobject"));
		// System.out.println("came-------------------"+new
		// String(allParams.get("sampleobject")));
		// ObjectInputStream oi1 = new ObjectInputStream(bi1);
		// Object obj1 = oi1.readObject();
		// System.out.println("came-------------------");
		// System.out.println("obj1111111111" + obj1);

		/*
		 * try { Reader in = new BufferedReader(new
		 * InputStreamReader(httpServletRequest.getInputStream()));
		 * 
		 * //this line of code is not all working ..even if we converted to bytes and
		 * send to the class
		 * 
		 * for (int c; (c = in.read()) >= 0;) System.out.print((char) c);
		 * 
		 * InputStream is = httpServletRequest.getInputStream(); byte[] bytes =
		 * StreamUtils.copyToByteArray(is); ByteArrayInputStream bi = new
		 * ByteArrayInputStream(allParams.get("sampleobject")); ObjectInputStream oi =
		 * new ObjectInputStream(bi); Object obj = oi.readObject();
		 * System.out.println("obj" + obj);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * System.out.println(new String(allParams.get("sampleobject")) +
		 * "value================");
		 * 
		 * ExObject exObject; try { exObject = mapper.readValue(new
		 * String(allParams.get("sampleobject")), ExObject.class);
		 * System.out.println(exObject + "exObject"); } catch (JsonMappingException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// System.out.println("EXObject is"+exObject);

		return ResponseEntity.ok(outputMovie);

		// InputStream is;

	}
	
	
	@GetMapping("/getData")
	public String getMessage() {
		return "Accessed by HTTPS protocol";
	}

}
