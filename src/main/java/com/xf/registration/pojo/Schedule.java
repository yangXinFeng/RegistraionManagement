package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int doctorId;
	private java.util.Date date;
	private int num1;
	private int num2;
	private int num3;


}