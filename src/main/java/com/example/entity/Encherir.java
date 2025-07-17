package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "encheres")
public class Encherir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "La date est requise")
    private LocalDateTime date;
    
    @NotNull(message = "Le prix est requis")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    private Double prix;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message = "Le client est requis")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "produit_id")
    @NotNull(message = "Le produit est requis")
    private Produit produit;
    
    // Constructeurs
    public Encherir() {}
    
    public Encherir(LocalDateTime date, Double prix, Client client, Produit produit) {
        this.date = date;
        this.prix = prix;
        this.client = client;
        this.produit = produit;
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
}