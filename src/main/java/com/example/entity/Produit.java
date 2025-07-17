package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le libellé est requis")
    private String libelle;
    
    @NotNull(message = "Le prix initial est requis")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    private Double prix;
    
    @NotBlank(message = "Le statut est requis")
    private String statut = "DISPONIBLE"; // DISPONIBLE, EN_COURS, VENDU
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<Encherir> encheres;
    
    // Constructeurs
    public Produit() {}
    
    public Produit(String libelle, Double prix, String statut) {
        this.libelle = libelle;
        this.prix = prix;
        this.statut = statut;
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    
    public List<Encherir> getEncheres() { return encheres; }
    public void setEncheres(List<Encherir> encheres) { this.encheres = encheres; }
}