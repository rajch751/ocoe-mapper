package com.uob.xml.parse.ocoemapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class MapperConfiguration {

	@Bean("xmlMapper")
	public XmlMapper xmlMapper() {

		XmlMapper xmlMapper = new XmlMapper();

		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;
		return new XmlMapper();
	}

}
