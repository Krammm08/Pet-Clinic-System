package com.app.service.impl;

import com.app.dao.AppointmentDAO;
import com.app.dao.impl.AppointmentDAOImpl;
import com.app.model.Appointment;
import com.app.service.AppointmentService;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public boolean addAppointment(Appointment appointment) {
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

        return appointmentDAO.addAppointment(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentID) {
        if (appointmentID <= 0) {
            System.out.println("Validation Error: Invalid Appointment ID.");
            return null;
        }
        return appointmentDAO.getAppointmentById(appointmentID);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        if (appointment.getAppointmentID() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Appointment ID.");
            return false;
        }

        if (appointment.getAppointmentDate() == null || appointment.getAppointmentTime() == null) {
            System.out.println("Validation Error: Date and Time cannot be empty.");
            return false;
        }

        return appointmentDAO.updateAppointment(appointment);
    }

    @Override
    public boolean deleteAppointment(int appointmentID) {
        if (appointmentID <= 0) {
            System.out.println("Validation Error: Invalid Appointment ID provided for deletion.");
            return false;
        }

        return appointmentDAO.deleteAppointment(appointmentID);
    }
}