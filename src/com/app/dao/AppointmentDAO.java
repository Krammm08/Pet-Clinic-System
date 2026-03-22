package com.app.dao;

import java.util.List;
import com.app.model.Appointment;
import com.app.exception.DatabaseException;

public interface AppointmentDAO {

    // Create appointment
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
