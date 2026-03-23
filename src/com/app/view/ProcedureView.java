package com.app.view;

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
            System.out.println("\t========== PROCEDURE MANAGEMENT ==========");
            System.out.println("\t|\t1. View All Procedures");
            System.out.println("\t|\t2. Update Diagnosis & Prescribe Medicine");
            System.out.println("\t|\t3. Back");
            System.out.println("\t==========================================");
            // Note: Adjust your switch(choice) to point option 2 to the new method below!
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");

            switch (choice) {
                case 1:
                    viewProcedures();
                    break;
                case 2:
                    updateProcedure();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\tX Invalid choice.");
            }
        }
    }

    // CREATE PROCEDURE
    private void createProcedure() {
        Procedure p = new Procedure();

        p.setAppointmentId(InputUtil.getInt("\tAppointment ID: "));
        p.setUserId(InputUtil.getInt("\tUser ID (Owner): "));
        p.setPetId(InputUtil.getInt("\tPet ID: "));
        p.setVetId(InputUtil.getInt("\tVet ID: "));
        p.setServiceId(InputUtil.getInt("\tService ID: "));
        p.setMedicineId(InputUtil.getInt("\tMedicine ID (0 if none): "));
        p.setDiagnosis(InputUtil.getNonEmptyString("\tDiagnosis: "));

        // Setting today's date automatically
        p.setProcedureDate(LocalDate.now().toString());

        // FIXED: Changed createProcedure to addProcedure to match the Service!
        boolean success = procedureService.addProcedure(p);

        if (success) {
            System.out.println("\t-> Procedure created successfully!");
            System.out.println("\t-> Transaction automatically generated."); // Note: Make sure your DB triggers handle this, or add logic to do it!
        } else {
            System.out.println("\tX Failed to create procedure. Please check your inputs.");
        }
    }

    // VIEW ALL PROCEDURES
    private void viewProcedures() {
        // No try-catch needed here because the DAO handles errors internally!
        List<Procedure> list = procedureService.getAllProcedures();

        System.out.println("\n\t===== ALL PROCEDURES =====");

        if (list.isEmpty()) {
            System.out.println("\tNo procedures found.");
            return;
        }
        for (Procedure p : list) {
            System.out.println("\tProcedure ID: " + p.getProcedureId());
            System.out.println("\tAppointment ID: " + p.getAppointmentId());
            System.out.println("\tUser ID: " + p.getUserId());
            System.out.println("\tPet ID: " + p.getPetId());
            System.out.println("\tVet ID: " + p.getVetId());
            System.out.println("\tService ID: " + p.getServiceId());
            System.out.println("\tMedicine: " + p.getMedicineName());
            System.out.println("\tDiagnosis: " + p.getDiagnosis());
            System.out.println("\tDate: " + p.getProcedureDate());
            System.out.println("\t---------------------------");
        }
    }
    private void updateProcedure() {
        System.out.println("\n\t--- UPDATE DIAGNOSIS & PRESCRIBE ---");

        int procedureId = InputUtil.getInt("\tEnter Procedure ID to update: ");
        String diagnosis = InputUtil.getString("\tEnter Vet's Diagnosis (e.g., 'Severe Flea Infection'): ");

        System.out.println("\n\t[Tip: Enter '1' if no medicine is needed]");
        int medicineId = InputUtil.getInt("\tEnter Prescribed Medicine ID: ");

        System.out.println("\tProcessing medical record and updating billing...");

        // Call the super-method we just built!
        boolean success = procedureService.updateProcedureAndBill(procedureId, diagnosis, medicineId);

        if (success) {
            System.out.println("\t-> SUCCESS! Medical record updated.");
            if (medicineId != 1) {
                System.out.println("\t-> Medicine dispensed. Cost has been added to the customer's bill.");
            }
        } else {
            System.out.println("\tX Failed to update procedure.");
        }
    }
}