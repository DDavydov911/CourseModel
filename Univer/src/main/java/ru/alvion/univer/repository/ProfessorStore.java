package ru.alvion.univer.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.alvion.univer.model.Professor;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
@Data
public class ProfessorStore {
    private AtomicInteger id = new AtomicInteger(0);
    private ConcurrentHashMap<Integer, Professor> professors = new ConcurrentHashMap<>();

    public ProfessorStore() {
        professors.put(id.incrementAndGet(),
                new Professor(id.get(), "Ivanov",
                        "Moscow...", "+79998887766", 3200F, null)
        );
        professors.put(id.incrementAndGet(),
                new Professor(id.get(), "Petrov",
                        "S-Petersburd...", "+79998887755", 3500F, null)
        );
        professors.put(id.incrementAndGet(),
                new Professor(id.get(), "Sidorov",
                        "Kazan...", "+79998887744", 3200F, null)
        );
    }

    public Optional<Professor> save(Professor professor) {
        if (professor.getId() == 0) {
            professor.setId(id.incrementAndGet());
            professors.put(professor.getId(), professor);
            return Optional.of(professor);
        }
        professors.replace(professor.getId(), professor);
        Professor professorDB = professors.get(professor.getId());
        return Optional.ofNullable(professorDB);
    }

    public Optional<Professor> getById(int professorId) {
        return Optional.ofNullable(professors.get(professorId));
    }

    public Collection<Professor> getAll() {
        return professors.values();
    }

    public Optional<Professor> delete(int professorId) {
        return Optional.ofNullable(professors.remove(professorId));
    }
}
