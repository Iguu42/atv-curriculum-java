package com.example.curriculum.controller;

import com.example.curriculum.model.Education;
import com.example.curriculum.model.Personal;
import com.example.curriculum.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private EducationRepository educationRepository;

    @GetMapping
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Education getEducationById(@PathVariable Long id) {
        return educationRepository.findById(id).orElseThrow(() -> new RuntimeException("Education not found"));
    }

    @PostMapping
    public ResponseEntity<?> createEducation(@RequestBody Education education, @RequestParam("personalId") int personalId) {
        Personal personal = new Personal();
        personal.setId(personalId);
        education.setPersonal(personal);
        Education savedEducation = educationRepository.save(education);
        return ResponseEntity.ok(savedEducation);
    }

}
