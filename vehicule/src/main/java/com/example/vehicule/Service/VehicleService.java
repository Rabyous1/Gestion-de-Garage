package com.example.vehicule.Service;

import com.example.vehicule.Entity.Vehicle;
import com.example.vehicule.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    // Créer un véhicule
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Récupérer tous les véhicules
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Récupérer un véhicule par ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    // Mettre à jour un véhicule
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setLicensePlate(vehicleDetails.getLicensePlate());
            vehicle.setModel(vehicleDetails.getModel());
            vehicle.setBrand(vehicleDetails.getBrand());
            return vehicleRepository.save(vehicle);
        } else {
            return null;
        }
    }

    // Supprimer un véhicule
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
