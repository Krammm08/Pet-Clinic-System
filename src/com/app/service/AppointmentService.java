package com.app.service;

import com.app.model.Appointment;
import com.app.exception.DatabaseException; // Make sure this is imported!
import java.util.List;

public interface AppointmentService {

    // The throws DatabaseException must be here to match the Implementation!
    boolean addAppointment(Appointment appointment) throws DatabaseException;

    List<Appointment> getUserAppointments(int userId);

    List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException;

    List<Appointment> getAllAppointments() throws DatabaseException;

    Appointment getAppointmentById(int appointmentId) throws DatabaseException;

    boolean approveAppointment(int appointmentId) throws DatabaseException;

    boolean declineAppointment(int appointmentId) throws DatabaseException;
}