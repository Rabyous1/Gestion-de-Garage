package com.example.vehicule.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceDescription;
    private LocalDate serviceDate;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Getters, Setters, Constructors
}
