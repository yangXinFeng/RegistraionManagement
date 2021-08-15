package com.xf.registration.controller;

import com.google.gson.Gson;
import com.xf.registration.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class DoctorController {

    @Resource
    DoctorService doctorService;

    @ResponseBody
    @RequestMapping("/doctor")
    public String queryAllDoctor(){
        return new Gson().toJson(doctorService.queryAllDoctor());
    }
}
