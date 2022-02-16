package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.AppointmentService;
import com.example.DoctorAppointmentBooking.service.DepartmentService;
import com.example.DoctorAppointmentBooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Controller
public class PatientController {

    static String email,password;
    static Patient defaultPatient;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/login")
    public String loginForm() {
        return "patientLogin";
    }

    @GetMapping("/patientRegister")
    public String registrationForm() {
        return "patientRegister";
    }



    @PostMapping("/register")
    public String registration(HttpServletRequest request,Model model) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String mobile= request.getParameter("mobile");
        String password = request.getParameter("password");
        Patient patient= new Patient(name,mobile,email,password);
        if(!patientService.existsByEmail(email)) {
            patientService.createPatient(patient);
        } else {
            model.addAttribute("error","Email already taken please choose another");
            return "patientRegister";
        }
        return "patientLogin";
    }

    @GetMapping("/departments")
    public String departments(Model model){
        model.addAttribute("departments", departmentService.departmentList());
        return "departments";
    }

    @PostMapping("/departments")
    public String login(HttpServletRequest request, Model model){
        Patient patient=patientService.getUserByEmailAndPassword(request.getParameter("username"),request.getParameter("password"));
        if(!(Objects.isNull(patient))){
            email=request.getParameter("username");
            password=request.getParameter("password");
            defaultPatient=patient;
            model.addAttribute("departments", departmentService.departmentList());
            return "departments";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "patientLogin";
        }

    }

    @GetMapping("/updatePatient")
    public String updatePatientDetails(Principal principal ,Model model){
        String email=principal.getName();
        Patient patient=patientService.getUserByEmail(email);
        Integer patientId=patient.getPatientId();
        model.addAttribute("patient",patientService.findById(patientId));
        return "updatePatient";
    }

    @PostMapping("/profile")
    public String profile(HttpServletRequest request,Model model){
        Patient patient=patientService.getUserByEmail(request.getParameter("email"));
        patient.setPatientName(request.getParameter("patientName"));
        patient.setPatientMobile(request.getParameter("patientMobile"));
        patient.setPassword(request.getParameter("password"));
        patientService.updatePatient(patient);
        model.addAttribute("patient",patient);
        return "profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal){
        String email=principal.getName();
        Patient patient=patientService.getUserByEmail(email);
        model.addAttribute("patient",patient);
        return "profile";
    }


    @GetMapping("/myAppointments")
    public String myAppointments(Model model, Principal principal){
        String email=principal.getName();
        Patient patient=patientService.getUserByEmail(email);
        Integer patientId=patient.getPatientId();
        model.addAttribute("appointments",appointmentService.myAppointmentList(patientId));
        return "myAppointments";
    }

}
