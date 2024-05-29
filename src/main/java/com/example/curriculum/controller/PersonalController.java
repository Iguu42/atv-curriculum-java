package com.example.curriculum.controller;

import com.example.curriculum.model.Personal;
import com.example.curriculum.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    @Autowired
    private PersonalRepository personalRepository;

    @GetMapping
    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Personal getPersonalById(@PathVariable int id) {
        return personalRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Personal not found"));
    }

    @PostMapping
    public Personal createPersonal(@RequestBody Personal personal) {
        return personalRepository.save(personal);
    }
}
