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
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer second;
	private double third;
	private String fourth;
	private String fifth;
	@Override
	public String toString() {
		return "ExObject [name=" + name + ", second=" + second + ", third=" + third + ", fourth=" + fourth + ", fifth="
				+ fifth + "]";
	}
	
	
	
	
	
	

}
