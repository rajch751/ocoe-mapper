package com.uob.xml.parse.ocoemapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "root")
public final class Root {
	
	private String firstElement;
	
	private AnotherRoot anotherroot;

	
	
	

}
