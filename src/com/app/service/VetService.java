package com.app.service;

import com.app.model.Vet;
import java.util.List;

public interface VetService {
    boolean addVet(Vet vet);
    List<Vet> getAllVets();
    Vet getVetById(int vetId);
    boolean updateVet(Vet vet);
    boolean deleteVet(int vetId);
}