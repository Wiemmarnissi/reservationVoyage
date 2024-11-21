package com.example.projetvoyage.controller;

import com.example.projetvoyage.entities.Voyage;
import com.example.projetvoyage.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @PostMapping
    public Voyage createVoyage(@RequestBody Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Voyage getVoyageById(@PathVariable Long id) {
        return voyageRepository.findById(id).orElseThrow(() -> new RuntimeException("Voyage not found"));
    }
}
