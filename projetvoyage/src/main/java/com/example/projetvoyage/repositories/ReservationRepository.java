package com.example.projetvoyage.repositories;

import com.example.projetvoyage.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByVoyageurId(Long voyageurId);
}
