package com.xf.registration.service;

import java.util.Date;

public interface RegisterService {

    int register(int doctorId, int patientId, Date date, int workTime);
}
