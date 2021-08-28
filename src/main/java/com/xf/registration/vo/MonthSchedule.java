package com.xf.registration.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthSchedule implements java.io.Serializable{

    private static final int CAN_REGISTER = 3;
    private int doctorId;
    private int years;
    private int months;
    private int days;
    private int[] available = new int[CAN_REGISTER];
    private int[] schedule = new int[CAN_REGISTER];

    public MonthSchedule(int doctorId, int years, int months, int days) {
        this.doctorId = doctorId;
        this.years = years;
        this.months = months;
        this.days = days;
    }
}
