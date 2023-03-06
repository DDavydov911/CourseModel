package ru.alvion.univer.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.univer.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class StudentsStore {
    private final AtomicInteger id = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, Student> students = new ConcurrentHashMap<>();

    public StudentsStore() {
        students.put(id.incrementAndGet(), new Student(
                id.get(), "Семёнова А.И.", "N.Novgorod",
                "+79991111111", 0, new ArrayList<>()
                )
        );
        students.put(id.incrementAndGet(), new Student(
                id.get(), "Григорьев В.А.", "Севастополь",
                "+79992222211", 0, new ArrayList<>()
                )
        );
        students.put(id.incrementAndGet(), new Student(
                        id.get(), "Арзамасцев К.В.", "Уренгой",
                "+79993333311", 0, new ArrayList<>()
                )
        );
    }

    public Optional<Student> save(Student student) {
        if (student.getRecordBookNumber() == 0) {
            student.setRecordBookNumber(id.incrementAndGet());
            students.put(student.getRecordBookNumber(), student);
            return Optional.of(student);
        }
        students.replace(student.getRecordBookNumber(), student);
        Student studentDB = students.get(student.getRecordBookNumber());
        return Optional.ofNullable(studentDB);
    }

    public Optional<Student> getById(int studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Optional<Student> delete(int studentId) {
        return Optional.ofNullable(students.remove(studentId));
    }
}
