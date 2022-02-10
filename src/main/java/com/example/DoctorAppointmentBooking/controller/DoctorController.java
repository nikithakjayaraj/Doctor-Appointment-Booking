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
import java.util.Objects;
import java.util.Optional;

@Controller
public class DoctorController {

    static String email,password;
    static Doctor defaultDoctor;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

//    @GetMapping("/doctorRegister")
//    public String registrationForm() {
//        return "doctorRegister";
//    }

//    @PostMapping("/doctorLogin")
//    public String registration(HttpServletRequest request) {
//        String email = request.getParameter("email");
//        String name = request.getParameter("name");
//        String mobile= request.getParameter("mobile");
//        String password = request.getParameter("password");
//        Doctor doctor= new Doctor(name,mobile,email,password);
//        doctorService.createDoctor(doctor);
//        return "doctorLogin";
//    }


    @GetMapping("/doctorLogin")
    public String loginForm() {
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
            Integer doctorId= defaultDoctor.getDoctorId();
            model.addAttribute("appointments",appointmentService.appointmentList(doctorId));

            return "appointmentList";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "doctorLogin";
        }

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




    @GetMapping("/updateDoctor/{doctorId}")
    public String updateDoctorDetails(@PathVariable Integer doctorId,Model model){
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
