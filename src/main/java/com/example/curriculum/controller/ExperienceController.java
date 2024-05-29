package com.example.curriculum.controller;

import com.example.curriculum.model.Experience;
import com.example.curriculum.model.Personal;
import com.example.curriculum.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceRepository experienceRepository;

    @GetMapping
    public List<Experience> getAllExperience() {
        return experienceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Experience getExperienceById(@PathVariable Long id) {
        return experienceRepository.findById(id).orElseThrow(() -> new RuntimeException("Experience not found"));
    }

    @PostMapping
    public ResponseEntity<?> createExperience(@RequestBody Experience experience,  @RequestParam("personalId") int personalId) {
        Personal personal = new Personal();
        personal.setId(personalId);
        experience.setPersonal(personal);
        Experience saveExperience = experienceRepository.save(experience);
        return ResponseEntity.ok(saveExperience);
    }
}
