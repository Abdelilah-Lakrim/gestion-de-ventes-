package com.example.controller;

import com.example.entity.Encherir;
import com.example.service.EncherirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/encheres")
@CrossOrigin(origins = "*")
public class EncherirController {
    
    @Autowired
    private EncherirService encherirService;
    
    @GetMapping
    public List<Encherir> getAllEncheres() {
        return encherirService.getAllEncheres();
    }
    
    @GetMapping("/produit/{produitId}")
    public List<Encherir> getEncheresByProduit(@PathVariable Long produitId) {
        return encherirService.getEncheresByProduit(produitId);
    }
    
    @GetMapping("/client/{clientId}")
    public List<Encherir> getEncheresByClient(@PathVariable Long clientId) {
        return encherirService.getEncheresByClient(clientId);
    }
    
    @PostMapping
    public ResponseEntity<Encherir> placerEnchere(
            @RequestParam Long clientId,
            @RequestParam Long produitId,
            @RequestParam Double prix) {
        try {
            Encherir enchere = encherirService.placerEnchere(clientId, produitId, prix);
            return ResponseEntity.ok(enchere);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/meilleure/{produitId}")
    public ResponseEntity<Encherir> getMeilleureEnchere(@PathVariable Long produitId) {
        return encherirService.getMeilleureEnchere(produitId)
            .map(enchere -> ResponseEntity.ok().body(enchere))
            .orElse(ResponseEntity.notFound().build());
    }
}