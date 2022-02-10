package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.AppointmentService;
import com.example.DoctorAppointmentBooking.service.DepartmentService;
import com.example.DoctorAppointmentBooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AppointmentService appointmentService;

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
            model.addAttribute("departments", departmentService.departmentList());
            return "departments";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "patientLogin";
        }

    }

    @GetMapping("/updatePatient/{patientId}")
    public String updatePatientDetails(@PathVariable Integer patientId,Model model){
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
//        model.addAttribute("patientName",patient.getPatientName());
//        model.addAttribute("email",patient.getEmail());
//        model.addAttribute("patientMobile",patient.getPatientMobile());
//        model.addAttribute("password",patient.getPassword());
        return "profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("patient",defaultPatient);
        return "profile";
    }


    @GetMapping("/myAppointments")
    public String myAppointments(Model model){
        Integer patientId=defaultPatient.getPatientId();
        model.addAttribute("appointments",appointmentService.myAppointmentList(patientId));
        return "myAppointments";
    }

}
