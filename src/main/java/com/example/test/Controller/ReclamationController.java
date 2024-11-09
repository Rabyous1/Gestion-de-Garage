package com.example.test.Controller;




import com.example.test.entity.Reclamation;
import com.example.test.service.IReclamationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    private final IReclamationService reclamationService;

    public ReclamationController(IReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    // Obtenir toutes les réclamations
    @GetMapping
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    // Obtenir une réclamation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        Optional<Reclamation> reclamation = reclamationService.getReclamationById(id);
        return reclamation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer une nouvelle réclamation
    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        if (reclamation.getDescription() == null || reclamation.getCreatedBy() == null) {
            return ResponseEntity.badRequest().build();
        }
        Reclamation newReclamation = reclamationService.createReclamation(reclamation.getDescription(), reclamation.getCreatedBy());
        return ResponseEntity.ok(newReclamation);
    }

    // Résoudre une réclamation par ID
    @PutMapping("/{id}/resolve")
    public ResponseEntity<Reclamation> resolveReclamation(@PathVariable Long id) {
        Reclamation resolvedReclamation = reclamationService.resolveReclamation(id);
        if (resolvedReclamation != null) {
            return ResponseEntity.ok(resolvedReclamation);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une réclamation par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }
}
