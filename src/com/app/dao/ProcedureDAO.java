package com.app.dao;

import com.app.exception.DatabaseException;
import com.app.model.Procedure;

import java.util.List;

public interface ProcedureDAO {
    boolean insertProcedure(Procedure procedure) throws DatabaseException;
    List<Procedure> getAllProcedures() throws DatabaseException;
    Procedure getProcedureById(int procedureId) throws DatabaseException;    boolean updateProcedure(Procedure procedure);
    boolean deleteProcedure(int id);
}
