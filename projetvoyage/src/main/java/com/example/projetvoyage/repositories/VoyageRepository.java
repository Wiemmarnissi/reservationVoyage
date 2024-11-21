package com.example.projetvoyage.repositories;

import com.example.projetvoyage.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByDestinationContainingIgnoreCase(String destination);
    List<Voyage> findByStatus(String status);
}
