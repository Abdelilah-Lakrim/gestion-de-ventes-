package com.example.service;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    
    public Client saveClient(Client client) {
        if (clientRepository.existsByUsername(client.getUsername())) {
            throw new RuntimeException("Un client avec ce nom d'utilisateur existe déjà");
        }
        return clientRepository.save(client);
    }
    
    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + id));
        
        client.setUsername(clientDetails.getUsername());
        client.setPassword(clientDetails.getPassword());
        client.setTelephone(clientDetails.getTelephone());
        client.setType(clientDetails.getType());
        
        return clientRepository.save(client);
    }
    
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + id));
        clientRepository.delete(client);
    }
}