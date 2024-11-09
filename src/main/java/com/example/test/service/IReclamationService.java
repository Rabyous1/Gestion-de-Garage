package com.example.test.service;



import com.example.test.entity.Reclamation;

import java.util.List;
import java.util.Optional;

public interface IReclamationService {

    List<Reclamation> getAllReclamations();

    Optional<Reclamation> getReclamationById(Long id);

    Reclamation createReclamation(String description, String createdBy);

    Reclamation resolveReclamation(Long id);

    void deleteReclamation(Long id);
}
