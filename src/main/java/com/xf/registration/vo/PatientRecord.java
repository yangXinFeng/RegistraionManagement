package com.xf.registration.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRecord implements java.io.Serializable {
    private String name;
    private String phone;
    private String workTime;
}
