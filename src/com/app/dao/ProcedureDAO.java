package com.app.dao;

import com.app.model.Medicine;
import com.app.model.Procedure;

import java.util.List;

public interface ProcedureDAO {
    boolean addProcedure(Procedure procedure);
    List<Procedure> getAllProcedure();
    Procedure getProcedureById(int id);
    boolean updateProcedure(Procedure procedure);
    boolean deleteProcedure(int id);
}
