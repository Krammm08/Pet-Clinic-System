package com.app.service;

import com.app.model.Appointment;
import com.app.exception.DatabaseException;
import java.util.List;

public interface AppointmentService {

    // Matches the new DAO methods exactly
    boolean createAppointment(Appointment appointment) throws DatabaseException;

    List<Appointment> getUserAppointments(int userId);

    List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException;

    List<Appointment> getAllAppointments() throws DatabaseException;

    Appointment getAppointmentById(int appointmentId) throws DatabaseException;

    boolean approveAppointment(int appointmentId) throws DatabaseException;

    boolean declineAppointment(int appointmentId) throws DatabaseException;

}