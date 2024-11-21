package com.example.projetvoyage.controller;

import com.example.projetvoyage.entities.Agence;
import com.example.projetvoyage.entities.Voyage;
import com.example.projetvoyage.repositories.AgenceRepository;
import com.example.projetvoyage.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private VoyageRepository voyageRepository;

    @PostMapping
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Agence getAgenceById(@PathVariable Long id) {
        return agenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Agence not found"));
    }

    @PostMapping("/{id}/voyages")
    public Voyage addVoyage(@PathVariable Long id, @RequestBody Voyage voyage) {
        Agence agence = agenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Agence not found"));
        voyage.setAgence(agence);
        voyage.setStatus("PENDING"); // Status initial
        return voyageRepository.save(voyage);
    }

    @PutMapping("/{agenceId}/voyages/{voyageId}")
    public Voyage modifyVoyage(@PathVariable Long agenceId, @PathVariable Long voyageId, @RequestBody Voyage updatedVoyage) {
        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new RuntimeException("Agence not found"));

        Voyage existingVoyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new RuntimeException("Voyage not found"));

        if (!existingVoyage.getAgence().getId().equals(agence.getId())) {
            throw new RuntimeException("Voyage does not belong to this agency");
        }
        existingVoyage.setDestination(updatedVoyage.getDestination());
        existingVoyage.setDateDepart(updatedVoyage.getDateDepart());
        existingVoyage.setDateArrivee(updatedVoyage.getDateArrivee());
        existingVoyage.setPrix(updatedVoyage.getPrix());
        existingVoyage.setStatus("MODIFIED_PENDING"); // Statut indiquant que la modification doit être validée

        return voyageRepository.save(existingVoyage);
    }
    @PutMapping("/voyages/{voyageId}/confirmation")
    public Voyage confirmModification(@PathVariable Long voyageId, @RequestParam boolean approved) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new RuntimeException("Voyage not found"));

        if ("MODIFIED_PENDING".equals(voyage.getStatus())) {
            if (approved) {
                voyage.setStatus("CONFIRMED");
            } else {
                voyage.setStatus("REJECTED");
            }
            return voyageRepository.save(voyage);
        } else {
            throw new RuntimeException("Voyage modification not in pending state");
        }
    }
    }


