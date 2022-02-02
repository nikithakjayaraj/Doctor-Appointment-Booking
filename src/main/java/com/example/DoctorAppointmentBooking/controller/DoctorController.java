package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class DoctorController {

    static String email,password;
    static Doctor defaultDoctor;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctorRegister")
    public String registrationForm() {
        return "doctorRegister";
    }

    @GetMapping("/doctorLogin")
    public String loginForm() {
        return "doctorLogin";
    }

    @PostMapping("/doctorLogin")
    public String registration(HttpServletRequest request) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String mobile= request.getParameter("mobile");
        String password = request.getParameter("password");
        Doctor doctor= new Doctor(name,mobile,email,password);
        doctorService.createDoctor(doctor);
        return "doctorLogin";
    }

    @GetMapping("/appointmentList")
    public String departments(){
        return "appointmentList";
    }

    @PostMapping("/appointmentList")
    public String login(HttpServletRequest request, Model model){
        Doctor doctor=doctorService.getDoctorByEmail(request.getParameter("email"));
        if(!(Objects.isNull(doctor))){
            email=request.getParameter("email");
            password=request.getParameter("password");
            defaultDoctor=doctor;

            return "home";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "patientLogin";
        }

    }
}
