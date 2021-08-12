package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String loginName;
	private String passWord;
	private String name;
	private int sex;
	private String email;
	private String phone;
	private String address;
	private int role;
	private java.util.Date createDate;
	private String active;


}