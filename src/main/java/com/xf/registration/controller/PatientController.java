package com.xf.registration.controller;

import com.google.gson.Gson;
import com.xf.registration.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Controller
public class PatientController {

    @Resource
    PatientService patientService;

    @RequestMapping("/validatepatient")
    public String validatePatient(@RequestBody Map<String, String> formData){
//        for(String key : formData.keySet()){
//            System.out.println(key + " " + formData.get(key));
//        }
        String res = "-1";
        String username = formData.get("username");
        String password = formData.get("password");
        Map<String,Object> map = patientService.getPassword(username);
        if (map.isEmpty()) return res;

        return map.get("password").equals(password)?map.get("patientId")+"":res;

    }

    @RequestMapping("/getpatient/{patientId}")
    public String getDoctorById(@PathVariable("patientId")int patientId){
        return new Gson().toJson(patientService.querryPatientById(patientId));
    }
}
