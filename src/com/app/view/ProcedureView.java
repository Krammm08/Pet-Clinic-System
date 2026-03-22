package com.app.view;

import com.app.exception.DatabaseException;
import com.app.exception.ValidationException;
import com.app.model.Procedure;
import com.app.service.ProcedureService;
import com.app.service.impl.ProcedureServiceImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

public class ProcedureView {

    private final ProcedureService procedureService = new ProcedureServiceImpl();
    private final Asciiart art = new Asciiart();

    public void show() {

        while (true) {
            
            System.out.println("\n\t========== PROCEDURE MANAGEMENT ==========");
            System.out.println("\t|\t1. Create Procedure");
            System.out.println("\t|\t2. View All Procedures");
            System.out.println("\t|\t3. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    createProcedure();
                    break;

                case 2:
                    viewProcedures();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // CREATE PROCEdure
    private void createProcedure() {
        try {
            Procedure p = new Procedure();

            p.setAppointmentId(InputUtil.getInt("Appointment ID: "));
            p.setUserId(InputUtil.getInt("User ID: "));
            p.setPetId(InputUtil.getInt("Pet ID: "));
            p.setVetId(InputUtil.getInt("Vet ID: "));
            p.setServiceId(InputUtil.getInt("Service ID: "));
            p.setMedicineId(InputUtil.getInt("Medicine ID: "));
            p.setDiagnosis(InputUtil.getNonEmptyString("Diagnosis: "));
            p.setProcedureDate(LocalDate.now().toString());

            boolean success = procedureService.createProcedure(p);

            if (success) {
                System.out.println("Procedure created successfully!");
                System.out.println("Transaction automatically generated.");
            } else {
                System.out.println("Failed to create procedure.");
            }

        } catch (ValidationException e) {
            System.out.println("X " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // VIEW ALL PROCEdureES
    private void viewProcedures() {
        try {
            List<Procedure> list = procedureService.getAllProcedures();

            System.out.println("\n===== ALL PROCEDURES =====");

            if (list.isEmpty()) {
                System.out.println("No procedures found.");
                return;
            }

            for (Procedure p : list) {
                System.out.println("Procedure ID: " + p.getProcedureId());
                System.out.println("Appointment ID: " + p.getAppointmentId());
                System.out.println("User ID: " + p.getUserId());
                System.out.println("Pet ID: " + p.getPetId());
                System.out.println("Vet ID: " + p.getVetId());
                System.out.println("Service ID: " + p.getServiceId());
                System.out.println("Medicine ID: " + p.getMedicineId());
                System.out.println("Diagnosis: " + p.getDiagnosis());
                System.out.println("Date: " + p.getProcedureDate());
                System.out.println("---------------------------");
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
