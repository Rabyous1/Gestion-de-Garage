package com.example.vehicule.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    // Getters, Setters, Constructors
}
