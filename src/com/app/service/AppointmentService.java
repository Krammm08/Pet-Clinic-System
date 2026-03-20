package com.app.service;

import com.app.model.Appointment;
import com.app.model.Pet;

import java.util.List;

public class AppointmentService {
    boolean addAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int appointmentID);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);
}