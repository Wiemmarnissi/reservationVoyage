package com.example.projetvoyage.repositories;

import com.example.projetvoyage.entities.Voyageur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {
    Optional<Voyageur> findByEmail(String email);
}
