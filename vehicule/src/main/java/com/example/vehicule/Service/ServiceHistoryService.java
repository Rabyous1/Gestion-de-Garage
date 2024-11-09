package com.example.vehicule.Service;

import com.example.vehicule.Entity.ServiceHistory;
import com.example.vehicule.repositories.ServiceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHistoryService {
    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;

    // Créer un historique de service pour un véhicule
    public ServiceHistory createServiceHistory(ServiceHistory serviceHistory) {
        return serviceHistoryRepository.save(serviceHistory);
    }

    // Obtenir tous les historiques pour un véhicule
    public List<ServiceHistory> getServiceHistoryByVehicle(Long vehicleId) {
        return serviceHistoryRepository.findByVehicleId(vehicleId);
    }
}
