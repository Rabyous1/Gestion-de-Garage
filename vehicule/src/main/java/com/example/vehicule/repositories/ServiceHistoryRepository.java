package com.example.vehicule.repositories;

import com.example.vehicule.Entity.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {
    List<ServiceHistory> findByVehicleId(Long vehicleId);
}
