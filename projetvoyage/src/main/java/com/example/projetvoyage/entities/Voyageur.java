package com.example.projetvoyage.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Voyageur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Long tel;

    @OneToMany(mappedBy = "voyageur", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
