package ru.alvion.univer.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.univer.model.Course;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseStore {
    private final AtomicInteger id = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, Course> courses = new ConcurrentHashMap<>();

    public CourseStore() {
        courses.put(id.incrementAndGet(),
                new Course(id.get(), "Economic", 300F, null, null)
        );
        courses.put(id.incrementAndGet(),
                new Course(id.get(), "Mathematics", 350F, null, null)
        );
        courses.put(id.incrementAndGet(),
                new Course(id.get(), "History", 200F, null, null)
        );
    }

    public Optional<Course> save(Course course) {
        if (course.getId() == 0) {
            course.setId(id.incrementAndGet());
            courses.put(course.getId(), course);
            return Optional.of(course);
        }
        courses.replace(course.getId(), course);
        Course courseDB = courses.get(course.getId());
        return Optional.ofNullable(courseDB);
    }

    public Optional<Course> getById(int courseId) {
        return Optional.ofNullable(courses.get(courseId));
    }

    public Collection<Course> getAll() {
        return courses.values();
    }

    public Optional<Course> delete(int courseId) {
        return Optional.ofNullable(courses.remove(courseId));
    }
}
