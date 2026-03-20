package com.app.dao;

import com.app.model.Appointment;
import java.util.List;

public interface AppointmentDAO {

    boolean addAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int appointmentId);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);

}