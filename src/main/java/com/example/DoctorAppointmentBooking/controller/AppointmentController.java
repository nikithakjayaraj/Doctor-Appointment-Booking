package com.example.DoctorAppointmentBooking.controller;

import com.example.DoctorAppointmentBooking.entity.Appointment;
import com.example.DoctorAppointmentBooking.entity.Department;
import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.entity.Patient;
import com.example.DoctorAppointmentBooking.service.AppointmentService;
import com.example.DoctorAppointmentBooking.service.DepartmentService;
import com.example.DoctorAppointmentBooking.service.DoctorService;
import com.example.DoctorAppointmentBooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;



@Controller
public class AppointmentController {

    static Doctor defaultDoctor;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PatientService patientService;



    @GetMapping("/doctor/{doctorId}")
    public String bookAppointment(@PathVariable Integer doctorId, Model model){
        defaultDoctor=doctorService.findByDoctorId(doctorId);
        Department department=defaultDoctor.getDepartment();
        model.addAttribute("doctorName",defaultDoctor.getDoctorName());
        model.addAttribute("departmentName",department.getDepartmentName());
        return "bookAppointment";
    }

    @PostMapping("/confirmation")
    public String appointment(HttpServletRequest request,Model model) throws ParseException {
        String patientName=request.getParameter("patientName");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String date=request.getParameter("date");
        String time= request.getParameter("time");
        String description=request.getParameter("description");
        Patient patient=patientService.getUserByEmail(email);
        Appointment appointment=new Appointment(date,time,patient,defaultDoctor,description);
        appointmentService.create(appointment);
        model.addAttribute("patientName",patientName);
        model.addAttribute("email",email);
        model.addAttribute("phone",phone);
        model.addAttribute("date",date);
        model.addAttribute("time",time);
        model.addAttribute("description",description);
        model.addAttribute("doctorName",defaultDoctor.getDoctorName());
        Department department=defaultDoctor.getDepartment();
        model.addAttribute("departmentName",department.getDepartmentName());
        return "confirmation";
    }


}
