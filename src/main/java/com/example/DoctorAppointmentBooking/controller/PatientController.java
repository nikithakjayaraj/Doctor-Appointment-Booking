package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class PatientController {

    static String email,password;
    static Patient defaultPatient;

    @Autowired
    private PatientService patientService;

    @GetMapping("/patientRegister")
    public String registrationForm() {
        return "patientRegister";
    }

    @GetMapping("/patientLogin")
    public String loginForm() {
        return "patientLogin";
    }

    @PostMapping("/patientLogin")
    public String registration(HttpServletRequest request) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String mobile= request.getParameter("mobile");
        String password = request.getParameter("password");
        Patient patient= new Patient(name,mobile,email,password);
        patientService.createPatient(patient);
        return "patientLogin";
    }

    @GetMapping("/departments")
    public String departments(){
        return "departments";
    }

    @PostMapping("/departments")
    public String login(HttpServletRequest request, Model model){
        Patient patient=patientService.getUserByEmail(request.getParameter("email"));
        if(!(Objects.isNull(patient))){
            email=request.getParameter("email");
            password=request.getParameter("password");
            defaultPatient=patient;

            return "home";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "patientLogin";
        }

    }
}
