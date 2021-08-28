package com.xf.registration.controller;

import com.google.gson.Gson;
import com.xf.registration.service.DoctorService;
import com.xf.registration.service.PartService;
import com.xf.registration.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@Controller
public class DoctorController {

    @Resource
    DoctorService doctorService;

    @Resource
    PartService partService;

    @Resource
    RegisterService registerService;

    @RequestMapping("/doctor")
    public String queryAllDoctor(){
        return new Gson().toJson(doctorService.queryAllDoctor());
    }

    @RequestMapping("/getpart")
    public String getPart(){
        return new Gson().toJson(partService.queryAllPart());
    }

    @RequestMapping("/getdoctors/{partCode}/{date}")
    public String getDoctros(@PathVariable("partCode") String partCode, @PathVariable("date")long date) throws ParseException {
        return new Gson().toJson(doctorService.queryDoctorByPartAndDate(partCode,new Date(date)));
    }

    @RequestMapping("/getSchedules/{doctorId}/{date}")
    public String getSchedules(@PathVariable("doctorId")int doctorId, @PathVariable("date")long date){
        Calendar calendar = Calendar.getInstance();
        Date start,end;
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DATE,1);
        start = calendar.getTime();
        calendar.set(Calendar.DATE,31);
        end = calendar.getTime();
        return new Gson().toJson(doctorService.querySchedules(doctorId,start,end));
    }

    @RequestMapping("/setSchedule/{doctorId}/{date}/{num1}/{num2}")
    public String setSchedule(@PathVariable("doctorId")int doctorId, @PathVariable("date")long date,
                              @PathVariable("num1")int num1,@PathVariable("num2")int num2){

        int[] num = new int[]{num1,num2,0};
        return doctorService.schedule(doctorId,new Date(date),num)+"";

    }

    @RequestMapping("/getReigsterPatients/{doctorId}/{date}")
    public String getReigsterPatients(@PathVariable("doctorId")int doctorId, @PathVariable("date")long date){
        return new Gson().toJson(registerService.queryPatientByDoctorAndDate(doctorId,new Date(date)));
    }

    @RequestMapping("/validatedoctor")
    public String validateDoctor(@RequestBody Map<String, String> formData){
//        for(String key : formData.keySet()){
//            System.out.println(key + " " + formData.get(key));
//        }
        String res = "-1";
        String username = formData.get("username");
        String password = formData.get("password");
        Map<String,Object> map = doctorService.getPassword(username);
        if (map.isEmpty()) return res;

        return map.get("password").equals(password)?map.get("doctorId")+"":res;

    }

    @RequestMapping("/getdoctor/{doctorId}")
    public String getDoctorById(@PathVariable("doctorId")int doctorId){
        return new Gson().toJson(doctorService.queryDoctorById(doctorId));
    }


}
