package com.app.service.impl;

import com.app.dao.AppointmentDAO;
import com.app.dao.impl.AppointmentDAOImpl;
import com.app.model.Appointment;
import com.app.service.AppointmentService;
import com.app.exception.DatabaseException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public void insertAppointment(Appointment appointment) throws DatabaseException {
        // 1. Validation Rules
        if (appointment == null) {
            throw new DatabaseException("Appointment data cannot be null.");
        }
        if (appointment.getUserID() <= 0) {
            throw new DatabaseException("Validation Error: Invalid User ID.");
        }
        if (appointment.getPetID() <= 0) {
            throw new DatabaseException("Validation Error: Invalid Pet ID.");
        }
        // NOTE: Ensure this getter matches your Appointment model (getServiceId or getServiceID)
        if (appointment.getServiceID() <= 0) {
            throw new DatabaseException("Validation Error: A valid Service ID must be selected.");
        }
        if (appointment.getAppointmentDate() == null || appointment.getAppointmentTime() == null) {
            throw new DatabaseException("Validation Error: Date and Time are required.");
        }

        // 2. The Triple Save starts here
        appointmentDAO.insertAppointment(appointment);
    }

    @Override
    public List<Appointment> getUserAppointments(int userId) {
        try {
            // This MUST call the DAO, otherwise the View gets an empty list
            return appointmentDAO.getAppointmentsByUserId(userId);
        } catch (DatabaseException e) {
            System.out.println("\t[DEBUG] Service Error: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException {
        if (userId <= 0) {
            return new ArrayList<>();
        }
        return appointmentDAO.getAppointmentsByUserId(userId);
    }

    @Override
    public List<Appointment> getAllAppointments() throws DatabaseException {
        return appointmentDAO.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) return null;
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    @Override
    public boolean approveAppointment(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) return false;
        return appointmentDAO.approveAppointment(appointmentId);
    }

    @Override
    public boolean declineAppointment(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) return false;
        return appointmentDAO.declineAppointment(appointmentId);
    }

    @Override
    public boolean updateAppointmentStatus(int appointmentId, String status) throws DatabaseException {
        if (appointmentId <= 0) return false;
        return appointmentDAO.updateAppointmentStatus(appointmentId, status);
    }
}