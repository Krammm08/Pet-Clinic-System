package com.app.dao;

import java.util.List;
import com.app.model.Appointment;
import com.app.exception.DatabaseException;

public interface AppointmentDAO {

    // 1. Create appointment (Handles Triple Save: App -> Proc -> Trans)
    void insertAppointment(Appointment appointment) throws DatabaseException;

    // 2. Get all appointments of a specific user
    List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException;

    // 3. Get all appointments (Admin dashboard)
    List<Appointment> getAllAppointments() throws DatabaseException;

    // 4. Get a single appointment by its ID
    Appointment getAppointmentById(int appointmentId) throws DatabaseException;

    // 5. Status Management
    boolean approveAppointment(int appointmentId) throws DatabaseException;
    boolean declineAppointment(int appointmentId) throws DatabaseException;

    // 6. General status update (Used by the Admin or System)
    boolean updateAppointmentStatus(int appointmentId, String status) throws DatabaseException;
}