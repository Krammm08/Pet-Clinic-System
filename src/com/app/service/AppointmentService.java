package com.app.service;

import com.app.model.Appointment;
import com.app.exception.DatabaseException; // Make sure this is imported!
import java.util.List;

public interface AppointmentService {

    boolean insertAppointment(Appointment appointment) throws DatabaseException;
    List<Appointment> getUserAppointments(int userId);

    // Get all appointments of a user
    List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException;

    // Get all appointments (Admin)
    List<Appointment> getAllAppointments() throws DatabaseException;

    // Get appointment by ID
    Appointment getAppointmentById(int appointmentId) throws DatabaseException;

    // Approve appointment
    boolean approveAppointment(int appointmentId) throws DatabaseException;

    // Decline appointment
    boolean declineAppointment(int appointmentId) throws DatabaseException;
}