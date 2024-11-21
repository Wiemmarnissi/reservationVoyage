package com.example.projetvoyage.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String destination;
    private LocalDate dateDepart;
    private LocalDate dateArrivee;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @OneToMany(mappedBy = "voyage", cascade = CascadeType.ALL)
    private List<Reservation> reservations;


    private String status ;;// cree enum
}
