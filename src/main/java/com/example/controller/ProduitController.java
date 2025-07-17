package com.example.controller;

import com.example.entity.Produit;
import com.example.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {
    
    @Autowired
    private ProduitService produitService;
    
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id)
            .map(produit -> ResponseEntity.ok().body(produit))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/statut/{statut}")
    public List<Produit> getProduitsByStatut(@PathVariable String statut) {
        return produitService.getProduitsByStatut(statut);
    }
    
    @PostMapping
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) {
        Produit savedProduit = produitService.saveProduit(produit);
        return ResponseEntity.ok(savedProduit);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @Valid @RequestBody Produit produitDetails) {
        try {
            Produit updatedProduit = produitService.updateProduit(id, produitDetails);
            return ResponseEntity.ok(updatedProduit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long id) {
        try {
            produitService.deleteProduit(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}