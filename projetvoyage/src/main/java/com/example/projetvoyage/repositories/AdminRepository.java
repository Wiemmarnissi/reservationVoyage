package com.example.projetvoyage.repositories;

import com.example.projetvoyage.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Recherche d'un admin par email pour la connexion
    Optional<Admin> findByEmail(String email);
}
