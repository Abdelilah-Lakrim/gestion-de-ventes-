package com.example.service;

import com.example.entity.Produit;
import com.example.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;
    
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }
    
    public List<Produit> getProduitsByStatut(String statut) {
        return produitRepository.findByStatut(statut);
    }
    
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    
    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id : " + id));
        
        produit.setLibelle(produitDetails.getLibelle());
        produit.setPrix(produitDetails.getPrix());
        produit.setStatut(produitDetails.getStatut());
        
        return produitRepository.save(produit);
    }
    
    public void deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id : " + id));
        produitRepository.delete(produit);
    }
}