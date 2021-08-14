package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Patient patient;
	private Doctor doctor;
	private java.util.Date date;


}