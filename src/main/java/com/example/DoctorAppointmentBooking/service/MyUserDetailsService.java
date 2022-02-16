package com.example.DoctorAppointmentBooking.service;

import com.example.DoctorAppointmentBooking.entity.Doctor;
import com.example.DoctorAppointmentBooking.entity.MyUserDetails;
import com.example.DoctorAppointmentBooking.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient=patientService.getUserByEmail(username);
        if(patient==null){
            Doctor doctor=doctorService.getDoctorByEmail(username);
            return new MyUserDetails(doctor.getEmail(),doctor.getPassword(),"ROLE_DOCTOR");
        }
        return new MyUserDetails(patient.getEmail(),patient.getPassword(),"ROLE_USER");

    }


}
