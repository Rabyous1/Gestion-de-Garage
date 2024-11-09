package com.example.test.service;



import com.example.test.entity.Reclamation;
import com.example.test.repository.ReclamationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService implements IReclamationService {

    private final ReclamationRepository reclamationRepository;

    public ReclamationService(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }

    @Override
    public Reclamation createReclamation(String description, String createdBy) {
        Reclamation reclamation = new Reclamation(description, "PENDING", createdBy, LocalDateTime.now());
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation resolveReclamation(Long id) {
        Optional<Reclamation> reclamationOpt = reclamationRepository.findById(id);
        if (reclamationOpt.isPresent()) {
            Reclamation reclamation = reclamationOpt.get();
            reclamation.setStatus("RESOLVED");
            reclamation.setResolvedAt(LocalDateTime.now());
            return reclamationRepository.save(reclamation);
        }
        return null;
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }
}
