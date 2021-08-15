package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String phone;
	private String address;

	public Patient(int id){this.id = id;}

	
}