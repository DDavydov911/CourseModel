package ru.alvion.univer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.univer.service.StudentService;
import ru.alvion.univer.model.Student;

import java.util.List;

@RestController
@RequestMapping("api/v1/univer/students")
public class StudentsRestContrller {

    private final StudentService service;

    public StudentsRestContrller(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {return service.getAll();}

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        if (student.getName() == null) {
            throw new NullPointerException("Name field must not be empty");
        }
        if (student.getAddress() == null) {
            throw new NullPointerException("Address field must not be empty");
        }
        if (student.getPhoneNumber() == null) {
            throw new NullPointerException("Phone Number field must not be empty");
        }

        return new ResponseEntity<>(
                service.save(student).get(),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        var studentDB = service.save(student).get();
        return new ResponseEntity<>(studentDB, HttpStatus.OK);
    }

    @GetMapping("/{sId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("sId") int id) {
        var studentDB = service.getStudentById(id);
        if (studentDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found");
        }
        return new ResponseEntity<>(studentDB.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{sId}")
    public ResponseEntity<Void> delete(@PathVariable("sId") int id) {
        var studentDB = service.deleteStudentById(id);
        return studentDB.isPresent() ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
