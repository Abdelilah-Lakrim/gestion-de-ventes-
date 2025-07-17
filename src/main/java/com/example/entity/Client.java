package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom d'utilisateur est requis")
    @Column(unique = true)
    private String username;
    
    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
    
    @NotBlank(message = "Le téléphone est requis")
    private String telephone;
    
    @NotBlank(message = "Le type est requis")
    private String type;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Encherir> encheres;
    
    // Constructeurs
    public Client() {}
    
    public Client(String username, String password, String telephone, String type) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.type = type;
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public List<Encherir> getEncheres() { return encheres; }
    public void setEncheres(List<Encherir> encheres) { this.encheres = encheres; }
}