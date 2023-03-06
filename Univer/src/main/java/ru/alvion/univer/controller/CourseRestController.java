package ru.alvion.univer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.univer.model.Course;
import ru.alvion.univer.service.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/univer/courses")
public class CourseRestController {
    private final CourseService service;

    public CourseRestController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        if (course.getCourseName() == null) {
            throw new NullPointerException("Name field must not be empty");
        }
        if (course.getPrice() == 0) {
            throw new NullPointerException("Description field must not be 0");
        }
        return new ResponseEntity<>(
                service.save(course).get(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{cId}")
    public ResponseEntity<Course> findById(@PathVariable("cId") int id) {
        Optional<Course> course = service.getById(id);
        if (course.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course is not found");
        }
        return new ResponseEntity<>(course.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody Course course) {
        var courseDB = service.save(course).get();
        return new ResponseEntity<>(courseDB, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        var res = service.deleteById(id);
        return res.isPresent() ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
