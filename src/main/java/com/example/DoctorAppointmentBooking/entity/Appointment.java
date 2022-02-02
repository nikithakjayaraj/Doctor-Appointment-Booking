package com.example.DoctorAppointmentBooking.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private Integer apppointmentId;

    private Date date;

    private Time time;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String description;

    public Appointment() {
    }

    public Appointment(Integer apppointmentId, Date date, Time time, String description) {
        this.apppointmentId = apppointmentId;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Integer getApppointmentId() {
        return apppointmentId;
    }

    public void setApppointmentId(Integer apppointmentId) {
        this.apppointmentId = apppointmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
