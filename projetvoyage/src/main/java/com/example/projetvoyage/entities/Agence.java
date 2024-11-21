package com.example.projetvoyage.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

    @Entity
    @Data
    public class Agence {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nomAgence;
        private String adresse;
        private String contact;

        @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
        private List<Voyage> voyages;
    }


