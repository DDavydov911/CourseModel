package ru.alvion.univer.service;

import org.springframework.stereotype.Service;
import ru.alvion.univer.model.Professor;
import ru.alvion.univer.repository.ProfessorStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorStore store;

    public ProfessorService(ProfessorStore store) {
        this.store = store;
    }

    public List<Professor> getProfessors() {
        return new ArrayList<>(store.getAll());
    }

    public Optional<Professor> save(Professor professor) {
        return store.save(professor);
    }

    public Optional<Professor> getProfessorById(int id) {
        return store.getById(id);
    }

    public Optional<Professor> deleteProfessorById(int id) {
        return store.delete(id);
    }
}
