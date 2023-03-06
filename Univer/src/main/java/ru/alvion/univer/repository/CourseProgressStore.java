package ru.alvion.univer.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.univer.model.Course;
import ru.alvion.univer.model.CourseProgress;
import ru.alvion.univer.model.Student;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseProgressStore {
    private final AtomicInteger id = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, CourseProgress> progresses =
            new ConcurrentHashMap<>();

//    public CourseProgressStore() {
//        progresses.put(id.incrementAndGet(), new CourseProgress(id.get(),
//                new Student(
//                        1, "Семёнова А.И.", "N.Novgorod",
//                        "+79991111111", 0, new ArrayList<>()),
//                new Course()
//        ));
//    }



}
