package com.app.dao;

import com.app.exception.DatabaseException;
import com.app.model.Vet;
import java.util.List;

public interface VetDAO {
    boolean addVet(Vet vet);
    boolean insertVet(Vet vet) throws DatabaseException;
    List<Vet> getAllVets() throws DatabaseException;
    Vet getVetById(int id);
    boolean updateVet(Vet vet);
    boolean deleteVet(int id);
}
