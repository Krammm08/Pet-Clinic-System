package com.app.service;

import com.app.model.Procedure;

import java.util.List;

public interface ProcedureService {
    boolean addProcedure(Procedure procedure);
    List<Procedure> getAllProcedure();
    Procedure getProcedureById(int id);
    boolean updateProcedure(Procedure procedure);
    boolean deleteProcedure(int id);
}
