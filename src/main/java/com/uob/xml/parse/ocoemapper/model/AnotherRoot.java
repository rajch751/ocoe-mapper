package com.uob.xml.parse.ocoemapper.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnotherRoot {
	
	@JacksonXmlElementWrapper(useWrapping = false)
    private List<SecondElement> secondElement = new ArrayList<>();

	
	
	

}
