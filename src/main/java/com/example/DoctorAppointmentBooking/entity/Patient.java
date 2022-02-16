package com.example.DoctorAppointmentBooking.entity;

import javax.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private Integer patientId;

    private String patientName;

    private String patientMobile;

    @Column(name="username",nullable = false,unique = true)
    private String email;

    private String password;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Patient(){

    }

    public Patient(String patientName, String patientMobile, String email, String password) {
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.email = email;
        this.password = password;
    }


    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
