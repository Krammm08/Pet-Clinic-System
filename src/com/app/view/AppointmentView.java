package com.app.view;

import com.app.model.Appointment;
import com.app.model.OfferedService;
import com.app.model.User;
import com.app.model.Pet;
import com.app.service.AppointmentService;
import com.app.service.OfferedServiceService;
import com.app.service.PetService;
import com.app.service.impl.AppointmentServiceImpl;
import com.app.service.impl.OfferedServiceServiceImpl;
import com.app.service.impl.PetServiceImpl;
import com.app.util.InputUtil;
import com.app.exception.DatabaseException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppointmentView {

    private final AppointmentService appointmentService = new AppointmentServiceImpl();
    private final OfferedServiceService offeredServiceService = new OfferedServiceServiceImpl();
    private final PetService petService = new PetServiceImpl();

    public void customerMenu(User user) {
        while (true) {
            System.out.println("\n\t============ APPOINTMENT MENU ============");
            System.out.println("\t|\t1. Book Appointment");
            System.out.println("\t|\t2. View My Appointments");
            System.out.println("\t|\t3. Back to Main Menu");
            System.out.println("\t==========================================");

            int choice = InputUtil.getInt("\tChoose option: ");

            switch (choice) {
                case 1: bookAppointment(user); break;
                case 2: viewUserAppointments(user); break;
                case 3: return;
                default: System.out.println("\tX Invalid choice.");
            }
        }
    }

    public void adminMenu() {
        while (true) {
            System.out.println("\n\t====== ADMIN APPOINTMENT MENU ======");
            System.out.println("\t|\t1. View All Appointments");
            System.out.println("\t|\t2. Approve Appointment");
            System.out.println("\t|\t3. Decline Appointment");
            System.out.println("\t|\t4. Back to Main Menu");
            System.out.println("\t====================================");

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

    private void bookAppointment(User user) {
        System.out.println("\n\t--- BOOK AN APPOINTMENT ---");

        try {
            System.out.println("\n\t--- AVAILABLE SERVICES ---");
            List<OfferedService> services = offeredServiceService.getAllServices();

            if (services.isEmpty()) {
                System.out.println("\t[!] No services available right now.");
                return;
            }

            for (OfferedService service : services) {
                System.out.println("\t[" + service.getServiceId() + "] "
                        + service.getServiceType() + " - Php "
                        + service.getServiceFee());
            }
            System.out.println("\t--------------------------");

            System.out.println("\n\t--- MY REGISTERED PETS ---");
            List<Pet> myPets = petService.getPetsByUser(user.getUserId());

            if (myPets.isEmpty()) {
                System.out.println("\t[!] You have no registered pets. Please register a pet first.");
                return;
            }

            for (Pet pet : myPets) {
                System.out.println("\t[" + pet.getPetId() + "] " + pet.getPetName()); // Changed getPetId to getPetID based on common naming, adjust if needed
            }
            System.out.println("\t--------------------------");

            int petId = InputUtil.getInt("\tEnter Pet ID: ");
            int serviceId = InputUtil.getInt("\tEnter Service ID: ");
            String dateStr = InputUtil.getString("\tDate (YYYY-MM-DD): ");
            String timeStr = InputUtil.getString("\tTime (HH:MM): ");

            Appointment newAppointment = new Appointment();
            newAppointment.setPetID(petId);
            newAppointment.setServiceId(serviceId);
            newAppointment.setUserID(user.getUserId());

            try {
                Date sqlDate = Date.valueOf(dateStr);
                Time sqlTime = Time.valueOf(timeStr + ":00");

                java.time.LocalDate inputDate = sqlDate.toLocalDate();
                java.time.LocalDate today = java.time.LocalDate.now();

                if (inputDate.isBefore(today)) {
                    System.out.println("\tX Booking failed: You cannot book an appointment in the past.");
                    return;
                }

                newAppointment.setAppointmentDate(sqlDate);
                newAppointment.setAppointmentTime(sqlTime);

            } catch (IllegalArgumentException e) {
                System.out.println("\tX Invalid date or time format! Please exactly match YYYY-MM-DD and HH:MM.");
                return;
            }

            // 1. Save the Appointment (This triggers the Triple Save inside DAO automatically!)
            appointmentService.insertAppointment(newAppointment);

            // 2. Display success messages
            System.out.println("\t-> Appointment booked successfully! Pending admin approval.");
            System.out.println("\t-> A pending bill has been added to your account.");
            System.out.println("\t-> You can pay for this in advance through the 'Billing & Transactions' menu.");

        } catch (DatabaseException e) {
            System.out.println("\tX Error booking appointment: " + e.getMessage());
        }
    }

    private void viewUserAppointments(User user) {
        System.out.println("\n\t--- MY APPOINTMENTS ---");
        try {
            List<Appointment> myAppointments = appointmentService.getAppointmentsByUserId(user.getUserId());

            if (myAppointments.isEmpty()) {
                System.out.println("\tYou don't have any appointments booked yet.");
                return;
            }

            System.out.println("\tID\tDate\t\tTime\tStatus");
            System.out.println("\t--------------------------------------------------");
            for (Appointment app : myAppointments) {
                System.out.println("\t[" + app.getAppointmentID() + "]\t"
                        + app.getAppointmentDate() + "\t"
                        + app.getAppointmentTime() + "\t"
                        + "[" + app.getIsApprove() + "]");
            }
        } catch (DatabaseException e) {
            System.out.println("\tX Error loading appointments: " + e.getMessage());
        }
    }

    private void viewAllAppointments() {
        System.out.println("\n\t--- ALL CLINIC APPOINTMENTS ---");
        try {
            List<Appointment> allAppointments = appointmentService.getAllAppointments();

            if (allAppointments.isEmpty()) {
                System.out.println("\tThere are no appointments in the system.");
                return;
            }

            System.out.println("\tID\tPet ID\tDate\t\tTime\tStatus");
            System.out.println("\t---------------------------------------------------------");
            for (Appointment app : allAppointments) {
                System.out.println("\t[" + app.getAppointmentID() + "]\t"
                        + app.getPetID() + "\t"
                        + app.getAppointmentDate() + "\t"
                        + app.getAppointmentTime() + "\t"
                        + "[" + app.getIsApprove() + "]");
            }
        } catch (DatabaseException e) {
            System.out.println("\tX Error loading appointments: " + e.getMessage());
        }
    }

    private void approveAppointment() {
        System.out.println("\n\t--- APPROVE APPOINTMENT ---");
        int appId = InputUtil.getInt("\tEnter Appointment ID to Approve: ");
        try {
            boolean success = appointmentService.updateAppointmentStatus(appId, "Approved");
            if (success) {
                System.out.println("\t-> Appointment [" + appId + "] has been APPROVED.");
            } else {
                System.out.println("\tX Failed to approve. Check if the ID exists.");
            }
        } catch (DatabaseException e) {
            System.out.println("\tX Database Error: " + e.getMessage());
        }
    }

    private void declineAppointment() {
        System.out.println("\n\t--- DECLINE APPOINTMENT ---");
        int appId = InputUtil.getInt("\tEnter Appointment ID to Decline: ");
        try {
            boolean success = appointmentService.updateAppointmentStatus(appId, "Declined");
            if (success) {
                System.out.println("\t-> Appointment [" + appId + "] has been DECLINED.");
            } else {
                System.out.println("\tX Failed to decline. Check if the ID exists.");
            }
        } catch (DatabaseException e) {
            System.out.println("\tX Database Error: " + e.getMessage());
        }
    }
}