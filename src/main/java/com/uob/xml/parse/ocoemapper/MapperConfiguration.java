package com.uob.xml.parse.ocoemapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class MapperConfiguration {
	
	
	@Bean("xmlMapper")
	public XmlMapper xmlMapper() {
		
		return new XmlMapper();
	}


}
