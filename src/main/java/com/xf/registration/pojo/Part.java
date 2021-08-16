package com.xf.registration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String partCode;
	private String name;
	private String remark;

	public Part(String partCode){
		this.partCode = partCode;
	}


}