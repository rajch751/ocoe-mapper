package com.uob.xml.parse.ocoemapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondElement {
	
	
	private String secondFirst;
	private String secondSecond;
	private String z;
	
	

}
