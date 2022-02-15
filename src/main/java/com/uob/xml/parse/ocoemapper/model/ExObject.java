package com.uob.xml.parse.ocoemapper.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include=Inclusion.NON_NULL)
public class ExObject implements Serializable {
	
	private String name;
	private Integer second;
	private double third;
	@Override
	public String toString() {
		return "{name=" + name + ", second=" + second + ", third=" + third + "}";
	};
	
	
	

}
