package com.example.controller;

import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
            .map(client -> ResponseEntity.ok().body(client))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        try {
            Client savedClient = clientService.saveClient(client);
            return ResponseEntity.ok(savedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        try {
            Client updatedClient = clientService.updateClient(id, clientDetails);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}