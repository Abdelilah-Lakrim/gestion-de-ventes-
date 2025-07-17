package com.example.repository;

import com.example.entity.Encherir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EncherirRepository extends JpaRepository<Encherir, Long> {
    List<Encherir> findByProduitId(Long produitId);
    List<Encherir> findByClientId(Long clientId);
    
    @Query("SELECT e FROM Encherir e WHERE e.produit.id = :produitId ORDER BY e.prix DESC")
    List<Encherir> findByProduitIdOrderByPrixDesc(@Param("produitId") Long produitId);
    
    @Query("SELECT e FROM Encherir e WHERE e.produit.id = :produitId AND e.prix = (SELECT MAX(e2.prix) FROM Encherir e2 WHERE e2.produit.id = :produitId)")
    Optional<Encherir> findHighestBidForProduct(@Param("produitId") Long produitId);
}