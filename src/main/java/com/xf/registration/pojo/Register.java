package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id = -1;
	private Patient patient = null;
	private Doctor doctor = null;
	private java.util.Date date = null;
	private int workTime = -1;
	//workTime 1:morning;2:afternoon;3:evening;

}