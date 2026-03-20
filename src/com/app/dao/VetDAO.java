package com.app.dao;

import com.app.model.Vet;
import java.util.List;

public interface VetDAO {
    boolean addVet(Vet vet);
    List<Vet> getAllVets();
    Vet getVetById(int id);
    boolean updateVet(Vet vet);
    boolean deleteVet(int id);
}
