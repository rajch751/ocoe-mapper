package com.uob.xml.parse.ocoemapper.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
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

	/*
	 * @PostMapping("/writeresposoutputstream") public ResponseEntity<String>
	 * postoutputstream(HttpServletRequest httpServletRequest) {
	 * 
	 * InputStream is; try { is = httpServletRequest.getInputStream();
	 * 
	 * byte[] bytes = StreamUtils.copyToByteArray(is); String output = new
	 * String(bytes);
	 * 
	 * // JwtRequest jwtreq = mapper.readValue((new String(bytes)),
	 * JwtRequest.class); // Object obj = convertBytesToObject(bytes); return
	 * ResponseEntity.ok(output);
	 * 
	 * }
	 * 
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * return ResponseEntity.badRequest().body("EXCEPTION" + e.getMessage()); }
	 * 
	 * }
	 * 
	 * @PostMapping("/writeresposoutputstreamAsObject") public
	 * ResponseEntity<Object> postoutputstreamAsObject(HttpServletRequest
	 * httpServletRequest, Object obj) {
	 * 
	 * InputStream is; try { is = httpServletRequest.getInputStream();
	 * 
	 * byte[] bytes = StreamUtils.copyToByteArray(is); String output = new
	 * String(bytes);
	 * 
	 * obj = mapper.readValue(output, Object.class);
	 * 
	 * // JwtRequest jwtreq = mapper.readValue((new String(bytes)),
	 * JwtRequest.class); // Object obj = convertBytesToObject(bytes); return
	 * ResponseEntity.ok(obj);
	 * 
	 * }
	 * 
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * return ResponseEntity.badRequest().body("EXCEPTION" + e.getMessage()); }
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/writeresposoutputstreamAsCreatedObject", method =
	 * RequestMethod.POST, consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE
	 * }) public ResponseEntity<MultiValueMap<String, String>>
	 * postoutputstreamAsObject(
	 * 
	 * @RequestParam MultiValueMap<String, String> allParams) {
	 * 
	 * System.out.println(allParams + "allParams"); return null;
	 * 
	 * // InputStream is;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = {
	 * MediaType.APPLICATION_FORM_URLENCODED_VALUE }) public ExObject
	 * postoutputstreamAsObject2(ExObject name, HttpServletRequest request) {
	 * 
	 * // System.out.println(allParams+"allParams");
	 * 
	 * 
	 * InputStream is;
	 * 
	 * StringBuilder sb = new StringBuilder();
	 * 
	 * sb.append("Request Method = [" + request.getMethod() + "], ");
	 * sb.append("Request URL Path = [" + request.getRequestURL() + "], ");
	 * 
	 * String headers = Collections.list(request.getHeaderNames()).stream()
	 * .map(headerName -> headerName + " : " +
	 * Collections.list(request.getHeaders(headerName)) )
	 * .collect(Collectors.joining(", "));
	 * 
	 * if (headers.isEmpty()) { sb.append("Request headers: NONE,"); } else {
	 * sb.append("Request headers: ["+headers+"],"); }
	 * 
	 * System.out.println(sb.toString());
	 * 
	 * Enumeration<String> params = request.getParameterNames();
	 * while(params.hasMoreElements()){ String paramName = params.nextElement();
	 * System.out.println("Parameter Name - "+paramName+", Value - "+request.
	 * getParameter(paramName)); }
	 * 
	 * InputStream is; try { // is = request.getInputStream();
	 * 
	 * Reader in = new BufferedReader(new
	 * InputStreamReader(request.getInputStream()));
	 * 
	 * for (int c; (c = in.read()) >= 0;) System.out.print((char) c); }
	 * 
	 * // Enumeration<String> params = request.getParameterNames();
	 * 
	 * // byte[] bytes = StreamUtils.copyToByteArray(is); // ObjectInputStream
	 * objinstream = new ObjectInputStream(is);
	 * 
	 * // InputStream targetStream = new ByteArrayInputStream(bytes);
	 * 
	 * // ObjectInputStream in = new ObjectInputStream(targetStream);
	 * 
	 * // Object o = in.readObject();
	 * 
	 * // Object obj=objinstream.readObject();
	 * 
	 * // System.out.println(o+"objinstream");
	 * 
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return name;
	 * 
	 * // InputStream is;
	 * 
	 * }
	 */
	@RequestMapping(value = "/postObjectvalue", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<ExObject> postoutputstreamAsObject122(@RequestParam Map<String, Object> allParams,
			HttpServletRequest httpServletRequest) throws ClassNotFoundException, IOException {

		// System.out.println(allParams+"allParams");
	//	System.out.println(allParams + "allParams");

		Object a = allParams.get("sampleobject");

		ExObject outputMovie = gson.fromJson(allParams.get("sampleobject").toString(), ExObject.class);

		 System.out.println(outputMovie);
		 
		 outputMovie.setName("JAYA");
		 outputMovie.setSecond(35);
		 outputMovie.setThird(3);
		 
		 //This code doesnot work 
		 
		 InputStream is = httpServletRequest.getInputStream(); 
		 byte[] bytes = StreamUtils.copyToByteArray(is); 
	// commented getting exception here line 190 -to 192
		 
	/*	 ByteArrayInputStream bi = new ByteArrayInputStream(bytes); 
		 ObjectInputStream oi = new ObjectInputStream(bi); 
		Object obj = oi.readObject();
		 System.out.println("obj" + obj);*/
		 
		 
		 // same code work for client and not her
		 
		 Reader in = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "UTF-8"));

		    for (int c; (c = in.read()) >= 0;)
		      System.out.print((char)c);
		  
		 
		 

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

}
