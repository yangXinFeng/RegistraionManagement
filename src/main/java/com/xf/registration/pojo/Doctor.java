package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private static final int CAN_REGISTER = 3;
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
	private int[] availableRegister = new int[CAN_REGISTER];
	private int[] scheduleRegister = new int[CAN_REGISTER];


}