package com.app.view;

import com.app.exception.DatabaseException;
import com.app.exception.ValidationException;
import com.app.model.Appointment;
import com.app.model.User;
import com.app.service.AppointmentService;
import com.app.service.impl.AppointmentServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

import java.util.List;

public class AppointmentView {

    private final AppointmentService appointmentService = new AppointmentServiceImpl();
    private final Asciiart art = new Asciiart();

    // CUSTOMER SIDE
    public void customerMenu(User user) {

        while (true) {
            
            System.out.println("\n\t============ APPOINTMENT MENU ============");
            System.out.println("\t|\t1. Book Appointment");
            System.out.println("\t|\t2. View My Appointments");
            System.out.println("\t|\t3. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    bookAppointment(user);
                    break;

                case 2:
                    viewUserAppointments(user);
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ADMIN SIDE
    public void adminMenu() {

        while (true) {
            System.out.println("\n===== MANAGE APPOINTMENTS =====");
            System.out.println("1. View All Appointments");
            System.out.println("2. Approve Appointment");
            System.out.println("3. Decline Appointment");
            System.out.println("4. Back");

            int choice = InputUtil.getInt("Choose option: ");

            switch (choice) {

                case 1:
                    viewAllAppointments();
                    break;

                case 2:
                    approveAppointment();
                    break;

                case 3:
                    declineAppointment();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // BOOK APPOINTMENT
    private void bookAppointment(User user) {
        try {
            Appointment appt = new Appointment();

            appt.setUserId(user.getUserId());
            appt.setPetId(InputUtil.getInt("Enter Pet ID: "));
            appt.setServiceId(InputUtil.getInt("Enter Service ID: "));
            appt.setAppointmentDate(InputUtil.getNonEmptyString("Date (YYYY-MM-DD): "));
            appt.setAppointmentTime(InputUtil.getNonEmptyString("Time (HH:MM): "));

            boolean success = appointmentService.createAppointment(appt);

            if (success) {
                System.out.println("Appointment booked! Waiting for approval.");
            } else {
                System.out.println("Failed to book appointment.");
            }

        } catch (ValidationException e) {
            System.out.println("X " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // VIEW USER APPOINTMENTS
    private void viewUserAppointments(User user) {
        try {
            List<Appointment> list = appointmentService.getUserAppointments(user.getUserId());

            System.out.println("\n===== MY APPOINTMENTS =====");

            if (list.isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }

            for (Appointment appt : list) {
                System.out.println("ID: " + appt.getAppointmentId());
                System.out.println("Pet ID: " + appt.getPetId());
                System.out.println("Service ID: " + appt.getServiceId());
                System.out.println("Date: " + appt.getAppointmentDate());
                System.out.println("Time: " + appt.getAppointmentTime());
                System.out.println("Status: " + getStatus(appt.getIsApprove()));
                System.out.println("------------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // VIEW ALL (ADMIN)
    private void viewAllAppointments() {
        try {
            List<Appointment> list = appointmentService.getAllAppointments();

            System.out.println("\n===== ALL APPOINTMENTS =====");

            if (list.isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }

            for (Appointment appt : list) {
                System.out.println("ID: " + appt.getAppointmentId());
                System.out.println("User ID: " + appt.getUserId());
                System.out.println("Pet ID: " + appt.getPetId());
                System.out.println("Service ID: " + appt.getServiceId());
                System.out.println("Date: " + appt.getAppointmentDate());
                System.out.println("Time: " + appt.getAppointmentTime());
                System.out.println("Status: " + getStatus(appt.getIsApprove()));
                System.out.println("------------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // APPROVE
    private void approveAppointment() {
        try {
            int id = InputUtil.getInt("Enter Appointment ID to approve: ");

            boolean success = appointmentService.approveAppointment(id);

            if (success) {
                System.out.println("Appointment approved.");
            } else {
                System.out.println("Failed to approve.");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // DECLINE
    private void declineAppointment() {
        try {
            int id = InputUtil.getInt("Enter Appointment ID to decline: ");

            boolean success = appointmentService.declineAppointment(id);

            if (success) {
                System.out.println("Appointment declined.");
            } else {
                System.out.println("Failed to decline.");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // STATUS HELPER
    private String getStatus(int status) {
        switch (status) {
            case 1: return "APPROVED";
            case 2: return "DECLINED";
            default: return "PENDING";
        }
    }
}
