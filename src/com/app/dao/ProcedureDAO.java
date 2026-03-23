package com.app.dao;

import com.app.model.Procedure;
import java.util.List;

public interface ProcedureDAO {
    boolean addProcedure(Procedure procedure);
    List<Procedure> getAllProcedures();
    Procedure getProcedureById(int procedureId);
    boolean updateProcedure(Procedure procedure);
    boolean deleteProcedure(int procedureId);
    boolean updateProcedureAndBill(int procedureId, String diagnosis, int medicineId);
}