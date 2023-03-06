package ru.alvion.univer.service;

import org.springframework.stereotype.Service;
import ru.alvion.univer.model.Professor;
import ru.alvion.univer.model.Student;
import ru.alvion.univer.repository.StudentsStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentsStore store;
    public StudentService(StudentsStore store) {
        this.store = store;
    }

    public List<Student> getAll() {
        return new ArrayList<>(store.getAll());
    }

    public Optional<Student> save(Student student) {
        return store.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return store.getById(id);
    }

    public Optional<Student> deleteStudentById(int id) {
        return store.delete(id);
    }
}
