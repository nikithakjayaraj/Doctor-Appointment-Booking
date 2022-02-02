package com.example.DoctorAppointmentBooking.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private Integer doctorId;

    private String doctorName;

    private String doctorMobile;

    @Column(name="username",nullable = false,unique = true)
    private String email;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private Set<Patient> patients;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private Set<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(String doctorName, String doctorMobile, String email, String password) {

        this.doctorName = doctorName;
        this.doctorMobile = doctorMobile;
        this.email = email;
        this.password = password;
    }


    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
