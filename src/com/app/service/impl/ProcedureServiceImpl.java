package com.app.service.impl;

import com.app.dao.ProcedureDAO;
import com.app.dao.impl.ProcedureDAOImpl;
import com.app.model.Procedure;
import com.app.service.ProcedureService;

import java.util.List;

public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureDAO procedureDAO = new ProcedureDAOImpl();

    @Override
    public boolean addProcedure(Procedure procedure) {
        // Validation 1: Check required relational IDs
        if (procedure.getAppointmentId() <= 0) {
            System.out.println("Validation Error: Procedure must be linked to a valid Appointment ID.");
            return false;
        }
        if (procedure.getServiceId() <= 0) {
            System.out.println("Validation Error: Procedure must be linked to a valid Service ID.");
            return false;
        }
        if (procedure.getVetId() <= 0) {
            System.out.println("Validation Error: A valid Vet ID is required.");
            return false;
        }
        if (procedure.getUserId() <= 0 || procedure.getPetId() <= 0) {
            System.out.println("Validation Error: Both User ID and Pet ID are required.");
            return false;
        }

        // Validation 2: The Vet must write a diagnosis/notes
        if (procedure.getDiagnosis() == null || procedure.getDiagnosis().trim().isEmpty()) {
            System.out.println("Validation Error: Diagnosis cannot be blank. The Vet must provide notes.");
            return false;
        }

        // Validation 3: Medicine ID check (0 means no medicine, but negative is invalid)
        if (procedure.getMedicineId() < 0) {
            System.out.println("Validation Error: Medicine ID cannot be negative.");
            return false;
        }

        return procedureDAO.addProcedure(procedure);
    }

    @Override
    public List<Procedure> getAllProcedures() {
        return procedureDAO.getAllProcedures();
    }

    @Override
    public Procedure getProcedureById(int procedureId) {
        if (procedureId <= 0) {
            System.out.println("Validation Error: Invalid Procedure ID.");
            return null;
        }
        return procedureDAO.getProcedureById(procedureId);
    }

    @Override
    public boolean updateProcedure(Procedure procedure) {
        if (procedure.getProcedureId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Procedure ID.");
            return false;
        }

        if (procedure.getDiagnosis() == null || procedure.getDiagnosis().trim().isEmpty()) {
            System.out.println("Validation Error: Diagnosis cannot be updated to blank.");
            return false;
        }

        return procedureDAO.updateProcedure(procedure);
    }

    @Override
    public boolean deleteProcedure(int procedureId) {
        if (procedureId <= 0) {
            System.out.println("Validation Error: Invalid Procedure ID provided for deletion.");
            return false;
        }
        return procedureDAO.deleteProcedure(procedureId);
    }
    @Override
    public boolean updateProcedureAndBill(int procedureId, String diagnosis, int medicineId) {
    return procedureDAO.updateProcedureAndBill(procedureId, diagnosis, medicineId);
    }
}