package ru.alvion.univer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.univer.model.Professor;
import ru.alvion.univer.service.ProfessorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/univer/professors")
public class ProfessorRestController {
    private final ProfessorService professorService;

    public ProfessorRestController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> getProfessors() {
        return professorService.getProfessors();
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        if (professor.getName() == null) {
            throw new NullPointerException("Name field must not be empty");
        }
        if (professor.getAddress() == null) {
            throw new NullPointerException("Description field must not be empty");
        }
        if (professor.getPhoneNumber() == null) {
            throw new NullPointerException("Phone Number field must not be empty");
        }
        return new ResponseEntity<>(
                professorService.save(professor).get(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{pId}")
    public ResponseEntity<Professor> findById(@PathVariable("pId") int id) {
        var professor = professorService.getProfessorById(id);
        if (professor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor is not found");
        }
        return new ResponseEntity<>(professor.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Professor> update(@RequestBody Professor professor) {
        var professorDB = professorService.save(professor).get();
        return new ResponseEntity<>(professorDB, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        var res = professorService.deleteProfessorById(id);
        return res.isPresent() ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
