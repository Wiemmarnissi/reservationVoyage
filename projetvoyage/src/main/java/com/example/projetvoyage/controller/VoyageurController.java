package com.example.projetvoyage.controller;

import com.example.projetvoyage.entities.Voyageur;
import com.example.projetvoyage.repositories.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voyageurs")
public class VoyageurController {

    @Autowired
    private VoyageurRepository voyageurRepository;

    @PostMapping
    public Voyageur createVoyageur(@RequestBody Voyageur voyageur) {
        return voyageurRepository.save(voyageur);
    }

    @GetMapping
    public List<Voyageur> getAllVoyageurs() {
        return voyageurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Voyageur getVoyageurById(@PathVariable Long id) {
        return voyageurRepository.findById(id).orElseThrow(() -> new RuntimeException("Voyageur not found"));
    }
}
