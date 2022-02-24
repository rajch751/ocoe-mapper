package com.uob.xml.parse.ocoemapper.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "EAI")
public class EAIClass {
	
	@JacksonXmlProperty(localName  = "BWH")
	private String bwh;
	
	
	@JacksonXmlProperty(localName  ="SvcRs")
	private SVCRS svcrs;
	
	

}
