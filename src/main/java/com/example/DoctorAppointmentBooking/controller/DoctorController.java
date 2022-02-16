package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Appointment;
import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.AppointmentService;
import com.example.DoctorAppointmentBooking.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class DoctorController {

    static String email,password;
    static Doctor defaultDoctor;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/appointmentList")
    public String departments(Model model, Principal principal){
        String email=principal.getName();
        defaultDoctor=doctorService.getDoctorByEmail(email);
        model.addAttribute("appointments",appointmentService.appointmentList(defaultDoctor.getDoctorId()));
        return "appointmentList";
    }


    @GetMapping("/department/{departmentId}")
    public String doctors(@PathVariable Integer departmentId, Model model){
        model.addAttribute("doctors",doctorService.doctorList(departmentId));
        return "doctors";
    }

    @GetMapping("/appointment/{appointmentId}")
    public String viewPatient(@PathVariable Integer appointmentId,Model model){
        Appointment appointment=appointmentService.findByAppointmentId(appointmentId);
        Patient patient=appointment.getPatient();
        model.addAttribute("patientName",patient.getPatientName());
        model.addAttribute("patientMobile",patient.getPatientMobile());
        model.addAttribute("patientEmail",patient.getEmail());
        return "viewPatient";
    }




    @GetMapping("/updateDoctor")
    public String updateDoctorDetails(Principal principal,Model model){
        String email=principal.getName();
        Doctor doctor=doctorService.getDoctorByEmail(email);
        Integer doctorId=doctor.getDoctorId();
        model.addAttribute("doctor",doctorService.findById(doctorId));
        return "updateDoctor";
    }

    @PostMapping("/profileDoctor")
    public String profile(HttpServletRequest request,Model model){
        Doctor doctor=doctorService.getDoctorByEmail(request.getParameter("email"));
        doctor.setDoctorName(request.getParameter("doctorName"));
        doctor.setDoctorMobile(request.getParameter("doctorMobile"));
        doctor.setPassword(request.getParameter("password"));
        doctorService.updateDoctor(doctor);
        model.addAttribute("doctor",doctor);
        return "profileDoctor";
    }

    @GetMapping("/profileDoctor")
    public String showProfile(Model model){
        model.addAttribute("doctor",defaultDoctor);
        return "profileDoctor";
    }

}
