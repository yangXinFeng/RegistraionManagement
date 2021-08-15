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
	private int doctor_id;
	private java.util.Date date;
	private int num1;
	private int num2;
	private int num3;

	public int getNum(int workTime){
		int res = num1;
		switch(workTime){
			case 2:res = num2;break;
			case 3:res = num3;break;
			default: res = num1;break;
		}
		return res;
	}

	public void setNum(int[] num){
		num1 = num[0];
		num2 = num[1];
		num3 = num[2];
	}


}