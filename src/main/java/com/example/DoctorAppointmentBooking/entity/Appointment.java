package com.example.DoctorAppointmentBooking.entity;


import javax.persistence.*;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private Integer appointmentId;

    private String date;

    private String time;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

    private String description;

    public Appointment() {
    }

    public Appointment(String date, String time, Patient patient, Doctor doctor, String description) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
