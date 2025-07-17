package com.example.service;

import com.example.entity.Encherir;
import com.example.entity.Produit;
import com.example.entity.Client;
import com.example.repository.EncherirRepository;
import com.example.repository.ProduitRepository;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EncherirService {
    
    @Autowired
    private EncherirRepository encherirRepository;
    
    @Autowired
    private ProduitRepository produitRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Encherir> getAllEncheres() {
        return encherirRepository.findAll();
    }
    
    public List<Encherir> getEncheresByProduit(Long produitId) {
        return encherirRepository.findByProduitIdOrderByPrixDesc(produitId);
    }
    
    public List<Encherir> getEncheresByClient(Long clientId) {
        return encherirRepository.findByClientId(clientId);
    }
    
    public Encherir placerEnchere(Long clientId, Long produitId, Double prix) {
        // Vérifier que le client existe
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + clientId));
        
        // Vérifier que le produit existe
        Produit produit = produitRepository.findById(produitId)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id : " + produitId));
        
        // Vérifier que le produit est disponible
        if (!"DISPONIBLE".equals(produit.getStatut())) {
            throw new RuntimeException("Ce produit n'est plus disponible aux enchères");
        }
        
        // Vérifier que le prix est supérieur au prix actuel (augmentation de 10%)
        Optional<Encherir> dernierEnchere = encherirRepository.findHighestBidForProduct(produitId);
        double prixMinimum = dernierEnchere.map(e -> e.getPrix() * 1.1).orElse(produit.getPrix() * 1.1);
        
        if (prix < prixMinimum) {
            throw new RuntimeException("Le prix doit être supérieur de 10% au prix actuel");
        }
        
        // Créer la nouvelle enchère
        Encherir enchere = new Encherir();
        enchere.setClient(client);
        enchere.setProduit(produit);
        enchere.setPrix(prix);
        enchere.setDate(LocalDateTime.now());
        
        return encherirRepository.save(enchere);
    }
    
    public Optional<Encherir> getMeilleureEnchere(Long produitId) {
        return encherirRepository.findHighestBidForProduct(produitId);
    }
}