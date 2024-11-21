package com.example.projetvoyage.controller;

import com.example.projetvoyage.entities.Reservation;
import com.example.projetvoyage.entities.Voyage;
import com.example.projetvoyage.entities.Voyageur;
import com.example.projetvoyage.repositories.ReservationRepository;
import com.example.projetvoyage.repositories.VoyageRepository;
import com.example.projetvoyage.repositories.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private VoyageurRepository voyageurRepository;
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }
    @PostMapping("/reserver")
    public Reservation reserverVoyage(
            @RequestParam Long voyageurId,
            @RequestParam Long voyageId) {

        // Vérifier si le voyage existe
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new RuntimeException("Voyage non trouvé"));

        // Vérifier si le voyageur existe
        Voyageur voyageur = voyageurRepository.findById(voyageurId)
                .orElseThrow(() -> new RuntimeException("Voyageur non trouvé"));

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setVoyage(voyage);
        reservation.setVoyageur(voyageur);
        reservation.setDateReservation(LocalDate.now());
        reservation.setStatus("En attente");
        reservation.setPrixTotal(voyage.getPrix());  // Calculer le prix total du voyage

        // Sauvegarder la réservation
        return reservationRepository.save(reservation);
    }
}
