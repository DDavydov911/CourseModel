package ru.alvion.univer.service;

import org.springframework.stereotype.Service;
import ru.alvion.univer.model.Course;
import ru.alvion.univer.repository.CourseStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseStore store;

    public CourseService(CourseStore store) {
        this.store = store;
    }

    public List<Course> getAll() {
        return new ArrayList<>(store.getAll());
    }

    public Optional<Course> save(Course course) {
        return store.save(course);
    }

    public Optional<Course> getById(int id) {
        return store.getById(id);
    }

    public Optional<Course> deleteById(int id) {
        return store.delete(id);
    }
}
