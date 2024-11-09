package com.example.vehicule.controllers;

import com.example.vehicule.Entity.ServiceHistory;
import com.example.vehicule.Service.ServiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historique")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceHistoryController {
    @Autowired
    private ServiceHistoryService serviceHistoryService;

    // Créer un nouvel historique de service
    @PostMapping
    public ResponseEntity<ServiceHistory> createServiceHistory(@RequestBody ServiceHistory serviceHistory) {
        ServiceHistory newHistory = serviceHistoryService.createServiceHistory(serviceHistory);
        return new ResponseEntity<>(newHistory, HttpStatus.CREATED);
    }

    // Récupérer tous les historiques d'un véhicule par ID de véhicule
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceHistory>> getServiceHistoryByVehicle(@PathVariable Long vehicleId) {
        List<ServiceHistory> historyList = serviceHistoryService.getServiceHistoryByVehicle(vehicleId);
        return new ResponseEntity<>(historyList, HttpStatus.OK);
    }
}
