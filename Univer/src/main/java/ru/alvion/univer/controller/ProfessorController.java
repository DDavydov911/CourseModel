package ru.alvion.univer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alvion.univer.model.Professor;
import ru.alvion.univer.service.ProfessorService;

@Controller
@RequestMapping("/api/v2/univer/professors")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public String getProfessors(Model model) {
        model.addAttribute("professors", service.getProfessors());
        return "index";
    }

    @GetMapping("/add")
    public String addFormProfessor() {
        return "addProfessor";
    }

    @GetMapping("/{pId}")
    public String getProfessorById(Model model, @PathVariable("pId") int id) {
        model.addAttribute("professor", service.getProfessorById(id).get());
        return "professorForm";
    }

    @PostMapping("/add")
    public String saveProfessor(@ModelAttribute Professor professor) {
        Professor professorDB = service.save(professor).get();
        return "redirect:/api/v2/univer/professors/" + professorDB.getId();
    }

    @GetMapping("/{pId}/update")
    public String updateProfessorForm(@PathVariable("pId") int id,
                                      Model model) {
        Professor professorDB = service.getProfessorById(id).get();
        model.addAttribute("professor", professorDB);
        return "updateProfessorForm";
    }

    @PostMapping("/update")
    public String updateProfessor(@ModelAttribute Professor professor) {
        Professor professorDB = service.save(professor).get();
        return "redirect:/api/v2/univer/professors/" + professorDB.getId();
    }

    @PostMapping("/{pId}/delete")
    public String deleteProfessor(@PathVariable("pId") int id) {
        service.deleteProfessorById(id);
        return "redirect:/api/v2/univer/professors";
    }
}
