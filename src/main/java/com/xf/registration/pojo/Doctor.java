package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String info;
	private double price;
	private String image;
	private int sex;
	private String email;
	private String phone;
	private String address;
	private java.util.Date createDate;
	private Part part;
	private String dept;


}