package com.example.projetvoyage.controller;

import com.example.projetvoyage.entities.Admin;
import com.example.projetvoyage.entities.Agence;
import com.example.projetvoyage.entities.Voyage;
import com.example.projetvoyage.entities.Voyageur;
import com.example.projetvoyage.repositories.AdminRepository;
import com.example.projetvoyage.repositories.AgenceRepository;
import com.example.projetvoyage.repositories.VoyageRepository;
import com.example.projetvoyage.repositories.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private VoyageurRepository voyageurRepository;
    @Autowired
    private VoyageRepository voyageRepository;


    //////////////////////////   Gestion des Admins   //////////////////
    // (Les méthodes CRUD pour Admin sont ici)

    //////////////////////////   Gestion des Agences   //////////////////

    // Ajouter une nouvelle agence
    @PostMapping("/agences")
    public Agence createAgence(@RequestBody Agence newAgence) {
        return agenceRepository.save(newAgence);
    }

    // Obtenir toutes les agences
    @GetMapping("/agences")
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    // Mettre à jour une agence
    @PutMapping("/agences/{id}")
    public Agence updateAgence(@PathVariable Long id, @RequestBody Agence agenceDetails) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        agence.setNomAgence(agenceDetails.getNomAgence());
        agence.setAdresse(agenceDetails.getAdresse());
        agence.setContact(agenceDetails.getContact());

        return agenceRepository.save(agence);
    }

    // Supprimer une agence
    @DeleteMapping("/agences/{id}")
    public String deleteAgence(@PathVariable Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));
        agenceRepository.delete(agence);
        return "Agence supprimée avec succès";
    }

    //////////////////////////   Gestion des Voyageurs   //////////////////

    // Ajouter un nouveau voyageur
    @PostMapping("/voyageurs")
    public Voyageur createVoyageur(@RequestBody Voyageur newVoyageur) {
        return voyageurRepository.save(newVoyageur);
    }

    // Obtenir tous les voyageurs
    @GetMapping("/voyageurs")
    public List<Voyageur> getAllVoyageurs() {
        return voyageurRepository.findAll();
    }

    // Mettre à jour un voyageur
    @PutMapping("/voyageurs/{id}")
    public Voyageur updateVoyageur(@PathVariable Long id, @RequestBody Voyageur voyageurDetails) {
        Voyageur voyageur = voyageurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyageur non trouvé"));

        voyageur.setNom(voyageurDetails.getNom());
        voyageur.setPrenom(voyageurDetails.getPrenom());
        voyageur.setEmail(voyageurDetails.getEmail());
        voyageur.setTel(voyageurDetails.getTel());

        return voyageurRepository.save(voyageur);
    }

    // Supprimer un voyageur
    @DeleteMapping("/voyageurs/{id}")
    public String deleteVoyageur(@PathVariable Long id) {
        Voyageur voyageur = voyageurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyageur non trouvé"));
        voyageurRepository.delete(voyageur);
        return "Voyageur supprimé avec succès";
    }
    @GetMapping("/voyages/pending")
    public List<Voyage> getPendingVoyages() {
        return voyageRepository.findByStatus("PENDING");
    }
    // Valider un voyage
    @PutMapping("/voyages/{id}/approve")
    public Voyage approveVoyage(@PathVariable Long id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new RuntimeException("Voyage not found"));
        voyage.setStatus("APPROVED");
        return voyageRepository.save(voyage);
    }

    // Rejeter un voyage
    @PutMapping("/voyages/{id}/reject")
    public Voyage rejectVoyage(@PathVariable Long id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new RuntimeException("Voyage not found"));
        voyage.setStatus("REJECTED");
        return voyageRepository.save(voyage);
    }

}
