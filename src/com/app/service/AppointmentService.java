package com.app.service;

import com.app.model.Appointment;
import java.util.List;

public interface AppointmentService {

    boolean addAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int appointmentId);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);

}