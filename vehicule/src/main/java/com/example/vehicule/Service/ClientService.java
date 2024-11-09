package com.example.vehicule.Service;

import com.example.vehicule.Entity.Client;
import com.example.vehicule.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    // Créer un client
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Récupérer un client par ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    // Mettre à jour un client
    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        if (client != null) {
            client.setName(clientDetails.getName());
            client.setEmail(clientDetails.getEmail());
            client.setPhoneNumber(clientDetails.getPhoneNumber());
            return clientRepository.save(client);
        } else {
            return null;
        }
    }

    // Supprimer un client
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
