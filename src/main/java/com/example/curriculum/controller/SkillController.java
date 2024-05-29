package com.example.curriculum.controller;

import com.example.curriculum.model.Personal;
import com.example.curriculum.model.Skill;
import com.example.curriculum.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skill, @RequestParam("personalId") int personalId) {
        Personal personal = new Personal();
        personal.setId(personalId);
        skill.setPersonal(personal);
        Skill savedSkill = skillRepository.save(skill);
        return ResponseEntity.ok(savedSkill);
    }
}
