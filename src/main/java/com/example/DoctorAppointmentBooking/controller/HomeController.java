package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.DoctorService;
import com.example.DoctorAppointmentBooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/success")
    public String success(Principal principal){
        String email=principal.getName();
        Patient patient=patientService.getUserByEmail(email);
        if(patient==null){
            return "redirect:/appointmentList";
        }
        return "redirect:/departments";
    }
}
