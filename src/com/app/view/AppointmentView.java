package com.app.view;

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
                case 1: bookAppointment(user); break;
                case 2: viewUserAppointments(user); break;
                case 3: return;
                default: System.out.println("\tX Invalid choice.");
            }
        }
    }

    // ADMIN SIDE
    public void adminMenu() {
        while (true) {
            System.out.println("\n\t===== MANAGE APPOINTMENTS =====");
            System.out.println("\t|\t1. View All Appointments");
            System.out.println("\t|\t2. Approve Appointment");
            System.out.println("\t|\t3. Decline Appointment");
            System.out.println("\t|\t4. Back");
            System.out.println("\t===============================");
            int choice = InputUtil.getInt("\tChoose option: ");

            switch (choice) {
                case 1: viewAllAppointments(); break;
                case 2: approveAppointment(); break;
                case 3: declineAppointment(); break;
                case 4: return;
                default: System.out.println("\tX Invalid choice.");
            }
        }
    }

    // BOOK APPOINTMENT
    private void bookAppointment(User user) {
        Appointment appt = new Appointment();

        appt.setUserId(user.getUserId());
        appt.setPetId(InputUtil.getInt("\tEnter Pet ID: "));
        appt.setServiceId(InputUtil.getInt("\tEnter Service ID: "));

        // FIX: Converting the String from the keyboard into a SQL Date/Time format!
        try {
            String dateInput = InputUtil.getNonEmptyString("\tDate (YYYY-MM-DD): ");
            appt.setAppointmentDate(java.sql.Date.valueOf(dateInput));

            String timeInput = InputUtil.getNonEmptyString("\tTime (HH:MM): ");
            // SQL Time requires seconds, so we automatically add ":00" to the end
            appt.setAppointmentTime(java.sql.Time.valueOf(timeInput + ":00"));
        } catch (IllegalArgumentException e) {
            System.out.println("\tX Invalid Date or Time format! Please use YYYY-MM-DD and HH:MM.");
            return; // Stop the process if they type a bad date
        }

        // FIX: Method name changed to match your Service implementation
        boolean success = appointmentService.addAppointment(appt);

        if (success) {
            System.out.println("\t-> Appointment booked! Waiting for Admin approval.");
        } else {
            System.out.println("\tX Failed to book appointment.");
        }
    }

    // VIEW USER APPOINTMENTS
    private void viewUserAppointments(User user) {
        List<Appointment> list = appointmentService.getUserAppointments(user.getUserId());

        System.out.println("\n\t===== MY APPOINTMENTS =====");

        if (list.isEmpty()) {
            System.out.println("\tNo appointments found.");
            return;
        }

        for (Appointment appt : list) {
            System.out.println("\tID: " + appt.getAppointmentId());
            System.out.println("\tPet ID: " + appt.getPetId());
            System.out.println("\tService ID: " + appt.getServiceId());
            System.out.println("\tDate: " + appt.getAppointmentDate());
            System.out.println("\tTime: " + appt.getAppointmentTime());
            System.out.println("\tStatus: " + getStatus(appt.getIsApprove()));
            System.out.println("\t------------------------");
        }
    }

    // VIEW ALL (ADMIN)
    private void viewAllAppointments() {
        List<Appointment> list = appointmentService.getAllAppointments();

        System.out.println("\n\t===== ALL APPOINTMENTS =====");

        if (list.isEmpty()) {
            System.out.println("\tNo appointments found.");
            return;
        }

        for (Appointment appt : list) {
            System.out.println("\tID: " + appt.getAppointmentId());
            System.out.println("\tUser ID: " + appt.getUserId());
            System.out.println("\tPet ID: " + appt.getPetId());
            System.out.println("\tService ID: " + appt.getServiceId());
            System.out.println("\tDate: " + appt.getAppointmentDate());
            System.out.println("\tTime: " + appt.getAppointmentTime());
            System.out.println("\tStatus: " + getStatus(appt.getIsApprove()));
            System.out.println("\t------------------------");
        }
    }

    // APPROVE (ADMIN)
    private void approveAppointment() {
        int id = InputUtil.getInt("\tEnter Appointment ID to approve: ");
        boolean success = appointmentService.approveAppointment(id);

        if (success) {
            System.out.println("\t-> Appointment approved.");
        } else {
            System.out.println("\tX Failed to approve. Check the ID.");
        }
    }

    // DECLINE (ADMIN)
    private void declineAppointment() {
        int id = InputUtil.getInt("\tEnter Appointment ID to decline: ");
        boolean success = appointmentService.declineAppointment(id);

        if (success) {
            System.out.println("\t-> Appointment declined.");
        } else {
            System.out.println("\tX Failed to decline. Check the ID.");
        }
    }

    // STATUS HELPER
    private String getStatus(int status) {
        switch (status) {
            case 1: return "APPROVED";
            case 2: return "DECLINED";
            default: return "PENDING"; // 0 usually means pending
        }
    }
}