package com.app.service.impl;

import com.app.dao.AppointmentDAO;
import com.app.dao.impl.AppointmentDAOImpl;
import com.app.model.Appointment;
import com.app.service.AppointmentService;
import com.app.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    // Connect to your newly updated DAO
    private AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public boolean createAppointment(Appointment appointment) throws DatabaseException {
        // Validation Rules
        if (appointment.getUserID() <= 0) {
            System.out.println("Validation Error: Appointment must be linked to a valid User ID.");
            return false;
        }

        if (appointment.getPetID() <= 0) {
            System.out.println("Validation Error: Appointment must be linked to a valid Pet ID.");
            return false;
        }

        if (appointment.getServiceID() <= 0) {
            System.out.println("Validation Error: A valid Service ID must be selected.");
            return false;
        }

        if (appointment.getAppointmentDate() == null) {
            System.out.println("Validation Error: Appointment date cannot be empty.");
            return false;
        }

        if (appointment.getAppointmentTime() == null) {
            System.out.println("Validation Error: Appointment time cannot be empty.");
            return false;
        }

        // If all validations pass, send it to the database
        return appointmentDAO.insertAppointment(appointment);
    }

    @Override
    public List<Appointment> getUserAppointments(int userId) {
        if (userId <= 0) return new ArrayList<>(); // basic validation
        return appointmentDAO.getUserAppointments(userId);
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(int userId) throws DatabaseException {
        if (userId <= 0) {
            System.out.println("Validation Error: Invalid User ID.");
            return new ArrayList<>(); // Return an empty list to prevent crashing
        }
        return appointmentDAO.getAppointmentsByUserId(userId);
    }

    @Override
    public List<Appointment> getAllAppointments() throws DatabaseException {
        // No strict validation needed for admins to view the full list
        return appointmentDAO.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) {
            System.out.println("Validation Error: Invalid Appointment ID.");
            return null;
        }
        return appointmentDAO.getAppointmentById(appointmentId);
    }

    @Override
    public boolean approveAppointment(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) {
            System.out.println("Validation Error: Invalid Appointment ID provided for approval.");
            return false;
        }
        return appointmentDAO.approveAppointment(appointmentId);
    }

    @Override
    public boolean declineAppointment(int appointmentId) throws DatabaseException {
        if (appointmentId <= 0) {
            System.out.println("Validation Error: Invalid Appointment ID provided for decline.");
            return false;
        }
        return appointmentDAO.declineAppointment(appointmentId);
    }
}