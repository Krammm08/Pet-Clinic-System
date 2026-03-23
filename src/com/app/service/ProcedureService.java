package com.app.service;

import com.app.model.Procedure;
import java.util.List;

public interface ProcedureService {
    boolean addProcedure(Procedure procedure);
    List<Procedure> getAllProcedures();
    Procedure getProcedureById(int procedureId);
    boolean updateProcedure(Procedure procedure);
    boolean deleteProcedure(int procedureId);
    // Add this inside ProcedureServiceImpl.java
    public boolean updateProcedureAndBill(int procedureId, String diagnosis, int medicineId);
}